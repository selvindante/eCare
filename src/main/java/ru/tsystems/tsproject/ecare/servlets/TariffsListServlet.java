package ru.tsystems.tsproject.ecare.servlets;

import ru.tsystems.tsproject.ecare.service.TariffService;
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
public class TariffsListServlet extends HttpServlet {
    TariffService tariffService = new TariffService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        List<Tariff> tariffs = null;
        switch (action) {
            case "saveOrUpdateTariff":
                req.getRequestDispatcher("/saveOrUpdateTariff.jsp").forward(req, resp);
                break;
            case "deleteAllTariffs":
                tariffService.deleteAllTariffs();
                tariffs = tariffService.getAllTariffs();
                req.setAttribute("role", "admin");
                req.setAttribute("tariffs", tariffs);
                req.getRequestDispatcher("/tariffsList.jsp").forward(req, resp);
                break;
            default: break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}