package ru.tsystems.tsproject.ecare.servlets;

import ru.tsystems.tsproject.ecare.ECareException;
import ru.tsystems.tsproject.ecare.Session;
import ru.tsystems.tsproject.ecare.entities.Client;
import ru.tsystems.tsproject.ecare.entities.Tariff;
import ru.tsystems.tsproject.ecare.service.ClientService;
import ru.tsystems.tsproject.ecare.service.IClientService;
import ru.tsystems.tsproject.ecare.service.ITariffService;
import ru.tsystems.tsproject.ecare.service.TariffService;
import ru.tsystems.tsproject.ecare.util.Util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Selvin
 * on 14.10.2014.
 */
public class DashboardServlet extends HttpServlet {
    IClientService clientService = ClientService.getInstance();
    ITariffService tariffService = TariffService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        Session session = Session.getInstance();
        session.setRole(req.getParameter("sessionRole"));
        session.setOn(Boolean.valueOf(req.getParameter("sessionStatus")));
        req.setAttribute("session", session);
        List<Client> clientsList = null;
        switch(action) {
            case "viewDashboard":
                clientsList = clientService.getAllClients();
                req.setAttribute("clientsList", clientsList);
                req.getRequestDispatcher("/dashboard.jsp").forward(req, resp);
                break;
            case "searchClientByNumber":
                try {
                    long number = Util.checkLong(req.getParameter("number"));
                    Client client = clientService.findClientByNumber(number);
                    req.setAttribute("client", client);
                    req.getRequestDispatcher("/client.jsp").forward(req, resp);
                } catch (ECareException ecx) {
                    clientsList = clientService.getAllClients();
                    req.setAttribute("clientsList", clientsList);
                    req.setAttribute("errormessage", ecx.getMessage());
                    req.getRequestDispatcher("/dashboard.jsp").forward(req, resp);
                }
                break;
            case "viewAllTariffs":
                List<Tariff> tariffs = tariffService.getAllTariffs();
                req.setAttribute("tariffs", tariffs);
                req.getRequestDispatcher("/tariffsList.jsp").forward(req, resp);
                break;
            case "deleteClient":
                long clientId = Long.valueOf(req.getParameter("id"));
                clientService.deleteClient(clientId);
                clientsList = clientService.getAllClients();
                req.setAttribute("clientsList", clientsList);
                req.getRequestDispatcher("/dashboard.jsp").forward(req, resp);
                break;
            case "deleteAllClients":
                clientService.deleteAllClients();
                clientsList = clientService.getAllClients();
                req.setAttribute("clientsList", clientsList);
                req.getRequestDispatcher("/dashboard.jsp").forward(req, resp);
                break;
            default: break;
        }
    }
}
