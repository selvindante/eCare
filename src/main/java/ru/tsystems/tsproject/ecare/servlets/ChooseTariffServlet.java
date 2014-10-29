package ru.tsystems.tsproject.ecare.servlets;

import ru.tsystems.tsproject.ecare.Session;
import ru.tsystems.tsproject.ecare.entities.Contract;
import ru.tsystems.tsproject.ecare.entities.Option;
import ru.tsystems.tsproject.ecare.entities.Tariff;
import ru.tsystems.tsproject.ecare.service.*;
import ru.tsystems.tsproject.ecare.util.PageName;

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
    IContractService contractService = ContractService.getInstance();
    ITariffService tariffService = TariffService.getInstance();
    IOptionService optionService = OptionService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long contractId = Long.valueOf(req.getParameter("contractId"));
        long tariffId = Long.valueOf(req.getParameter("tariffId"));
        String action = req.getParameter("action");
        Session session = Session.getInstance();
        session.setRole(req.getParameter("sessionRole"));
        session.setOn(Boolean.valueOf(req.getParameter("sessionStatus")));
        req.setAttribute("session", session);
        if(action.equals("chooseTariff")) {
            Contract contract = contractService.loadContract(contractId);
            Tariff tariff = tariffService.loadTariff(tariffId);
            List<Option> options = optionService.getAllOptionsForTariff(tariffId);
            req.setAttribute("contract", contract);
            req.setAttribute("tariff", tariff);
            req.setAttribute("options", options);
            req.setAttribute("pagename", PageName.CHOOSEOPTIONS.toString());
            req.getRequestDispatcher("/chooseOptions.jsp").forward(req, resp);
        }
    }
}
