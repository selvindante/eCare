package ru.tsystems.tsproject.ecare.servlets;

import ru.tsystems.tsproject.ecare.Session;
import ru.tsystems.tsproject.ecare.entities.Tariff;
import ru.tsystems.tsproject.ecare.service.ITariffService;
import ru.tsystems.tsproject.ecare.service.TariffService;

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
    ITariffService tariffService = TariffService.getInstance();

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
        String action = req.getParameter("action");
        Session session = Session.getInstance();
        session.setRole(req.getParameter("sessionRole"));
        session.setOn(Boolean.valueOf(req.getParameter("sessionStatus")));
        req.setAttribute("session", session);
        List<Tariff> tariffs = null;
        switch(action) {
            case "createTariff":
                req.getRequestDispatcher("/createTariff.jsp").forward(req, resp);
                break;
            case "deleteAllTariffs":
                tariffService.deleteAllTariffs();
                tariffs = tariffService.getAllTariffs();
                req.setAttribute("tariffs", tariffs);
                req.getRequestDispatcher("/tariffsList.jsp").forward(req, resp);
                break;
            default: break;
        }
    }
}
