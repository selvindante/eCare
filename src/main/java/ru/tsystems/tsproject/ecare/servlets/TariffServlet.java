package ru.tsystems.tsproject.ecare.servlets;

import ru.tsystems.tsproject.ecare.service.OptionService;
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
public class TariffServlet extends HttpServlet {
    TariffService tariffService = new TariffService();
    OptionService optionService = new OptionService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long tariffId = Long.valueOf(req.getParameter("id"));
        String action = req.getParameter("action");
        Tariff tariff = tariffService.loadTariff(tariffId);
        switch(action) {
            case "viewTariff":
                req.setAttribute("tariff", tariff);
                req.getRequestDispatcher("/tariff.jsp").forward(req, resp);
                break;
            case "createOption":
                req.getRequestDispatcher("/createOption.jsp").forward(req, resp);
            case "deleteTariff":
                tariffService.deleteTariff(tariffId);
                List<Tariff> tariffs = tariffService.getAllTariffs();
                req.setAttribute("role", "admin");
                req.setAttribute("tariffs", tariffs);
                req.getRequestDispatcher("/tariffsList.jsp").forward(req, resp);
                break;
            case "deleteAllOptions":
                optionService.deleteAllOptionsForTariff(tariffId);
                tariff.setOptions(optionService.getAllOptionsForTariff(tariffId));
                req.setAttribute("tariff", tariff);
                req.getRequestDispatcher("/tariff.jsp").forward(req, resp);
                break;
            default: break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        int price = Integer.valueOf(req.getParameter("price"));
        Tariff tariff = new Tariff(title, price);
        tariffService.createTariff(tariff);
        List<Tariff> tariffs = tariffService.getAllTariffs();
        req.setAttribute("role", "admin");
        req.setAttribute("tariffs", tariffs);
        req.getRequestDispatcher("/tariffsList.jsp").forward(req, resp);
    }
}
