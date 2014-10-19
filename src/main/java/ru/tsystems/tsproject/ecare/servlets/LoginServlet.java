package ru.tsystems.tsproject.ecare.servlets;

import ru.tsystems.tsproject.ecare.ECareException;
import ru.tsystems.tsproject.ecare.Session;
import ru.tsystems.tsproject.ecare.entities.Client;
import ru.tsystems.tsproject.ecare.service.ClientService;
import ru.tsystems.tsproject.ecare.service.IClientService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Selvin
 * on 12.10.2014.
 */
public class LoginServlet extends HttpServlet {
    IClientService clientService = ClientService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        Session session = Session.getInstance();
        switch (action) {
            case "login":
                String login = req.getParameter("login");
                String password = req.getParameter("password");
                try {
                    Client client = clientService.findClient(login, password);
                    req.setAttribute("role", client.getRole());
                    if(client.getRole().equals("client")) {
                        session.setRole("client");
                        session.setOn(true);
                        req.setAttribute("session", session);
                        req.setAttribute("client", client);
                        req.getRequestDispatcher("/client.jsp").forward(req, resp);
                    }
                    else if(client.getRole().equals("admin")){
                        List<Client> clientsList = clientService.getAllClients();
                        session.setRole("admin");
                        session.setOn(true);
                        req.setAttribute("session", session);
                        req.setAttribute("clientsList", clientsList);
                        req.getRequestDispatcher("/dashboard.jsp").forward(req, resp);
                    }
                } catch (ECareException ecx) {
                    req.setAttribute("message", ecx.getMessage());
                    req.getRequestDispatcher("/error.jsp").forward(req, resp);
                }
                break;
            case "logout":
                session.setOn(false);
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
                break;
            default: break;
        }
    }
}
