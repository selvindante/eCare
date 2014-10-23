package ru.tsystems.tsproject.ecare.servlets;

import ru.tsystems.tsproject.ecare.ECareException;
import ru.tsystems.tsproject.ecare.Session;
import ru.tsystems.tsproject.ecare.entities.Tariff;
import ru.tsystems.tsproject.ecare.service.IOptionService;
import ru.tsystems.tsproject.ecare.service.ITariffService;
import ru.tsystems.tsproject.ecare.service.OptionService;
import ru.tsystems.tsproject.ecare.service.TariffService;
import ru.tsystems.tsproject.ecare.util.Util;

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
    ITariffService tariffService = TariffService.getInstance();
    IOptionService optionService = OptionService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long tariffId = 0;
        String action = req.getParameter("action");
        Session session = Session.getInstance();
        session.setRole(req.getParameter("sessionRole"));
        session.setOn(Boolean.valueOf(req.getParameter("sessionStatus")));
        req.setAttribute("session", session);
        Tariff tariff = null;
        switch (action) {
            case "createTariff":
                try {
                    String title = Util.checkStringLength(req.getParameter("title"));
                    int price = Util.checkInt(req.getParameter("price"));
                    tariff = new Tariff(title, price);
                    tariff = tariffService.saveOrUpdateTariff(tariff);
                    req.setAttribute("tariff", tariff);
                    req.getRequestDispatcher("/tariff.jsp").forward(req, resp);
                } catch (ECareException ecx) {
                    req.setAttribute("errormessage", ecx.getMessage());
                    req.getRequestDispatcher("/createTariff.jsp").forward(req, resp);
                }
                break;
            case "viewTariff":
                tariffId = Long.valueOf(req.getParameter("id"));
                tariff = tariffService.loadTariff(tariffId);
                req.setAttribute("tariff", tariff);
                req.getRequestDispatcher("/tariff.jsp").forward(req, resp);
                break;
            case "deleteTariff":
                tariffId = Long.valueOf(req.getParameter("id"));
                tariffService.deleteTariff(tariffId);
                List<Tariff> tariffs = tariffService.getAllTariffs();
                req.setAttribute("tariffs", tariffs);
                req.getRequestDispatcher("/tariffsList.jsp").forward(req, resp);
                break;
            case "createOption":
                tariffId = Long.valueOf(req.getParameter("id"));
                tariff = tariffService.loadTariff(tariffId);
                req.setAttribute("tariff", tariff);
                req.getRequestDispatcher("/createOption.jsp").forward(req, resp);
                break;
            case "deleteAllOptions":
                tariffId = Long.valueOf(req.getParameter("id"));
                optionService.deleteAllOptionsForTariff(tariffId);
                tariff = tariffService.loadTariff(tariffId);
                tariff.setOptions(optionService.getAllOptionsForTariff(tariffId));
                req.setAttribute("tariff", tariff);
                req.getRequestDispatcher("/tariff.jsp").forward(req, resp);
                break;
            default: break;
        }
    }
}
