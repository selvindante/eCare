package ru.tsystems.tsproject.ecare.servlets;

import ru.tsystems.tsproject.ecare.ECareException;
import ru.tsystems.tsproject.ecare.Session;
import ru.tsystems.tsproject.ecare.entities.Client;
import ru.tsystems.tsproject.ecare.service.ClientService;
import ru.tsystems.tsproject.ecare.service.IClientService;
import ru.tsystems.tsproject.ecare.util.Util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Selvin
 * on 14.10.2014.
 */
public class ClientServlet extends HttpServlet {
    IClientService clientService = ClientService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*long id = Long.valueOf(req.getParameter("id"));
        String action = req.getParameter("action");
        switch (action) {
            case "saveOrUpdateContract":
                req.setAttribute("client", clientService.loadClient(id));
                req.getRequestDispatcher("/saveOrUpdateContract.jsp").forward(req, resp);
                break;
            default: break;
        }*/
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long clientId = Long.valueOf(req.getParameter("id"));
        String action = req.getParameter("action");
        Session session = Session.getInstance();
        session.setRole(req.getParameter("sessionRole"));
        session.setOn(Boolean.valueOf(req.getParameter("sessionStatus")));
        req.setAttribute("session", session);
        Client client = clientService.loadClient(clientId);
        req.setAttribute("client", client);
        switch(action) {
            case "viewClient":
                req.getRequestDispatcher("/client.jsp").forward(req, resp);
                break;
            case "createContract":
                req.getRequestDispatcher("/createContract.jsp").forward(req, resp);
                break;
            case "editClient":
                req.getRequestDispatcher("/editClient.jsp").forward(req, resp);
                break;
            case "updateClient":
                try {
                    client.setName(Util.checkStringLength(req.getParameter("name")));
                    client.setLastname(Util.checkStringLength(req.getParameter("lastname")));
                    client.setBirthDate(Util.checkDate(req.getParameter("birthdate")));
                    client.setPassport(Util.checkLong(req.getParameter("passport")));
                    client.setAddress(Util.checkStringLength(req.getParameter("address")));
                    client = clientService.saveOrUpdateClient(client);
                    req.setAttribute("client", client);
                    req.getRequestDispatcher("/client.jsp").forward(req, resp);
                } catch (ECareException ecx) {
                    req.setAttribute("errormessage", ecx.getMessage());
                    req.getRequestDispatcher("/editClient.jsp").forward(req, resp);
                }
                break;
            case "addAmount":
                try {
                    client.addAmount(Util.checkInt(req.getParameter("amount")));
                    client = clientService.saveOrUpdateClient(client);
                    req.setAttribute("client", client);
                    req.getRequestDispatcher("/client.jsp").forward(req, resp);
                } catch (ECareException ecx) {
                    req.setAttribute("errormessage", ecx.getMessage());
                    req.getRequestDispatcher("/client.jsp").forward(req, resp);
                }
            default:
                break;
        }
    }
}
