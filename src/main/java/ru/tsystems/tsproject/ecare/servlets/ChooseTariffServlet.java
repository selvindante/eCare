package ru.tsystems.tsproject.ecare.servlets;

import ru.tsystems.tsproject.ecare.business.ContractBusiness;
import ru.tsystems.tsproject.ecare.business.OptionBusiness;
import ru.tsystems.tsproject.ecare.business.TariffBusiness;
import ru.tsystems.tsproject.ecare.entities.Contract;
import ru.tsystems.tsproject.ecare.entities.Option;
import ru.tsystems.tsproject.ecare.entities.Tariff;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Selvin
 * on 15.10.2014.
 */
public class ChooseTariffServlet extends HttpServlet {
    ContractBusiness contractBusiness = new ContractBusiness();
    TariffBusiness tariffBusiness = new TariffBusiness();
    OptionBusiness optionBusiness = new OptionBusiness();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long contractId = Long.valueOf(req.getParameter("contractId"));
        long tariffId = Long.valueOf(req.getParameter("tariffId"));
        String action = req.getParameter("action");
        if(action.equals("chooseTariff")) {
            Contract contract = contractBusiness.loadContract(contractId);
            Tariff tariff = tariffBusiness.loadTariff(tariffId);
            List<Option> options = optionBusiness.getAllOptionsForTariff(tariffId);
            req.setAttribute("contract", contract);
            req.setAttribute("tariff", tariff);
            req.setAttribute("options", options);
            req.getRequestDispatcher("/chooseOptions.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
