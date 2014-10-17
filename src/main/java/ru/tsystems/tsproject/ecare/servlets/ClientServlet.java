package ru.tsystems.tsproject.ecare.servlets;

import ru.tsystems.tsproject.ecare.service.ClientService;

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
    ClientService clientService = new ClientService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.valueOf(req.getParameter("id"));
        String action = req.getParameter("action");
        switch (action) {
            case "saveOrUpdateContract":
                req.setAttribute("client", clientService.loadClient(id));
                req.getRequestDispatcher("/saveOrUpdateContract.jsp").forward(req, resp);
                break;
            default: break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
