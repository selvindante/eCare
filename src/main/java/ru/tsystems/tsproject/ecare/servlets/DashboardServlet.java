package ru.tsystems.tsproject.ecare.servlets;

import ru.tsystems.tsproject.ecare.ECareException;
import ru.tsystems.tsproject.ecare.business.ClientBusiness;
import ru.tsystems.tsproject.ecare.business.TariffBusiness;
import ru.tsystems.tsproject.ecare.entities.Client;
import ru.tsystems.tsproject.ecare.entities.Tariff;

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
    ClientBusiness clientBusiness = new ClientBusiness();
    TariffBusiness tariffBusiness = new TariffBusiness();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        List<Client> clientsList = null;
        switch (action) {
            case "viewClient":
                long id = Long.valueOf(req.getParameter("id"));
                Client client = clientBusiness.loadClient(id);
                req.setAttribute("role", "admin");
                req.setAttribute("client", client);
                req.getRequestDispatcher("/client.jsp").forward(req, resp);
                break;
            case "viewAllTariffs":
                List<Tariff> tariffs = tariffBusiness.getAllTariffs();
                req.setAttribute("role", "admin");
                req.setAttribute("tariffs", tariffs);
                req.getRequestDispatcher("/tariffsList.jsp").forward(req, resp);
                break;
            case "deleteAllClients":
                clientBusiness.deleteAllClients();
                req.setAttribute("role", "admin");
                clientsList = clientBusiness.getAllClients();
                req.setAttribute("clientsList", clientsList);
                req.getRequestDispatcher("/dashboard.jsp").forward(req, resp);
                break;
            case "deleteClient":
                long clientId = Long.valueOf(req.getParameter("id"));
                clientBusiness.deleteClient(clientId);
                clientsList = clientBusiness.getAllClients();
                req.setAttribute("role", "admin");
                req.setAttribute("clientsList", clientsList);
                req.getRequestDispatcher("/dashboard.jsp").forward(req, resp);
                break;
            default: break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long number = Long.valueOf(req.getParameter("number"));
        try {
            Client client = clientBusiness.findClientByNumber(number);
            req.setAttribute("role", "admin");
            req.setAttribute("client", client);
            req.getRequestDispatcher("/client.jsp").forward(req, resp);
        } catch (ECareException ecx) {
            req.setAttribute("message", ecx.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}
