package ru.tsystems.tsproject.ecare.servlets;

import ru.tsystems.tsproject.ecare.ECareException;
import ru.tsystems.tsproject.ecare.business.ClientBusiness;
import ru.tsystems.tsproject.ecare.entities.Client;

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
    ClientBusiness clientBusiness = new ClientBusiness();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            Client client = clientBusiness.findClient(login, password);
            req.setAttribute("role", client.getRole());
            if(client.getRole().equals("client")) {
                req.setAttribute("client", client);
                req.getRequestDispatcher("/client.jsp").forward(req, resp);
            }
            else if(client.getRole().equals("admin")){
                List<Client> clientsList = clientBusiness.getAllClients();
                req.setAttribute("clientsList", clientsList);
                req.getRequestDispatcher("/dashboard.jsp").forward(req, resp);
            }
        } catch (ECareException ecx) {
            req.setAttribute("message", ecx.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}
