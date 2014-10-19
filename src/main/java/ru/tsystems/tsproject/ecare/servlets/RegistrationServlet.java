package ru.tsystems.tsproject.ecare.servlets;

import ru.tsystems.tsproject.ecare.Session;
import ru.tsystems.tsproject.ecare.entities.Client;
import ru.tsystems.tsproject.ecare.service.ClientService;
import ru.tsystems.tsproject.ecare.service.IClientService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Selvin
 * on 14.10.2014.
 */
public class RegistrationServlet extends HttpServlet {
    IClientService clientService = ClientService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String lastname = req.getParameter("lastname");
        Date birthdate = java.sql.Date.valueOf(req.getParameter("birthdate"));
        long passport = Long.parseLong(req.getParameter("passport"));
        String address = req.getParameter("address");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Client client = new Client(name, lastname, birthdate, passport, address, email, password, "client", 0);
        clientService.saveOrUpdateClient(client);
        client = clientService.findClient(email, password);
        Session session = Session.getInstance();
        session.setRole("client");
        session.setOn(true);
        req.setAttribute("session", session);
        req.setAttribute("client", client);
        req.getRequestDispatcher("/client.jsp").forward(req, resp);
    }
}
