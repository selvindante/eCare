package ru.tsystems.tsproject.ecare.servlets;

import ru.tsystems.tsproject.ecare.business.TariffBusiness;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Selvin
 * on 14.10.2014.
 */
public class TariffsListServlet extends HttpServlet {
    TariffBusiness tariffBusiness = new TariffBusiness();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "createTariff":
                req.getRequestDispatcher("/createTariff.jsp").forward(req, resp);
                break;
            default: break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
