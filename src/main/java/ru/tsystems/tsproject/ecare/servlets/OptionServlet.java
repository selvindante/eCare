package ru.tsystems.tsproject.ecare.servlets;

import ru.tsystems.tsproject.ecare.business.OptionBusiness;
import ru.tsystems.tsproject.ecare.business.TariffBusiness;
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
    OptionBusiness optionBusiness = new OptionBusiness();
    TariffBusiness tariffBusiness = new TariffBusiness();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long tariffId = Long.valueOf(req.getParameter("id"));
        String title = req.getParameter("title");
        int price = Integer.valueOf(req.getParameter("price"));
        int costOfConnection = Integer.valueOf(req.getParameter("costOfConnection"));
        Tariff tariff = tariffBusiness.loadTariff(tariffId);
        Option option = new Option(tariff, title, price, costOfConnection);
        optionBusiness.createOption(option);
        tariff.addOption(option);
        req.setAttribute("tariff", tariff);
        req.getRequestDispatcher("/tariff.jsp").forward(req, resp);
    }
}
