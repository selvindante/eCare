package ru.tsystems.tsproject.ecare.web;

import ru.tsystems.tsproject.ecare.ECareException;
import ru.tsystems.tsproject.ecare.business.ClientBusiness;
import ru.tsystems.tsproject.ecare.entities.Client;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Selvin
 * on 12.10.2014.
 */
public class TestServlet extends HttpServlet {
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
            req.setAttribute("client", client);
            if(client.getRole().equals("client")) {
                req.getRequestDispatcher("/client.jsp").forward(req, resp);
            }
            else if(client.getRole().equals("admin")){
                req.getRequestDispatcher("/dashboard.jsp").forward(req, resp);
            }
        } catch (ECareException ecx) {
            req.setAttribute("message", ecx.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }

    }
}
