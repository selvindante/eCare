package ru.tsystems.tsproject.ecare.servlets;

import ru.tsystems.tsproject.ecare.business.ClientBusiness;

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
    ClientBusiness clientBusiness = new ClientBusiness();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.valueOf(req.getParameter("id"));
        String action = req.getParameter("action");
        switch (action) {
            case "createContract":
                req.setAttribute("client", clientBusiness.loadClient(id));
                req.getRequestDispatcher("/createContract.jsp").forward(req, resp);
                break;
            default: break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}