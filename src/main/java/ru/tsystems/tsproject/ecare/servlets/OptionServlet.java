package ru.tsystems.tsproject.ecare.servlets;

import ru.tsystems.tsproject.ecare.service.*;
import ru.tsystems.tsproject.ecare.entities.Option;
import ru.tsystems.tsproject.ecare.entities.Tariff;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Selvin
 * on 14.10.2014.
 */
public class OptionServlet extends HttpServlet {
    IOptionService optionService = OptionService.getInstance();
    ITariffService tariffService = TariffService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long optionId = Long.valueOf(req.getParameter("id"));
        String action = req.getParameter("action");
        Option option = optionService.loadOption(optionId);
        switch (action) {
            case "viewOption":
                req.setAttribute("option", option);
                req.getRequestDispatcher("/option.jsp").forward(req, resp);
                break;
            case "deleteOption":
                optionService.deleteOption(optionId);
                Tariff tariff = tariffService.loadTariff(option.getTariff().getId());
                req.setAttribute("tariff", tariff);
                req.getRequestDispatcher("/tariff.jsp").forward(req, resp);
                break;
            default: break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long tariffId = Long.valueOf(req.getParameter("id"));
        String title = req.getParameter("title");
        int price = Integer.valueOf(req.getParameter("price"));
        int costOfConnection = Integer.valueOf(req.getParameter("costOfConnection"));
        Tariff tariff = tariffService.loadTariff(tariffId);
        Option option = new Option(tariff, title, price, costOfConnection);
        optionService.saveOrUpdateOption(option);
        tariff.addOption(optionService.findOptionByTitleAndTariffId(title, tariffId));
        req.setAttribute("tariff", tariff);
        req.getRequestDispatcher("/tariff.jsp").forward(req, resp);
    }
}
