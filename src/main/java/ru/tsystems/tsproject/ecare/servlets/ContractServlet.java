package ru.tsystems.tsproject.ecare.servlets;

import ru.tsystems.tsproject.ecare.Session;
import ru.tsystems.tsproject.ecare.service.*;
import ru.tsystems.tsproject.ecare.entities.Client;
import ru.tsystems.tsproject.ecare.entities.Contract;
import ru.tsystems.tsproject.ecare.entities.Tariff;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Selvin
 * on 14.10.2014.
 */
public class ContractServlet extends HttpServlet {
    IContractService contractService = ContractService.getInstance();
    IClientService clientService = ClientService.getInstance();
    ITariffService tariffService = TariffService.getInstance();
    IOptionService optionService = OptionService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long contractId = Long.valueOf(req.getParameter("id"));
        String action = req.getParameter("action");
        Session session = Session.getInstance();
        session.setRole(req.getParameter("sessionRole"));
        session.setOn(Boolean.valueOf(req.getParameter("sessionStatus")));
        req.setAttribute("session", session);
        Contract contract = contractService.loadContract(contractId);
        Client client = null;
        switch(action) {
            case "viewContract":
                req.setAttribute("contract", contract);
                req.getRequestDispatcher("/contract.jsp").forward(req, resp);
                break;
            case "deleteContract":
                contractService.deleteContract(contractId);
                client = clientService.findClientByNumber(contract.getNumber());
                req.setAttribute("client", client);
                req.getRequestDispatcher("/client.jsp").forward(req, resp);
                break;
            case "blockByOperator":
                contractService.blockByOperator(contract);
                client = clientService.findClientByNumber(contract.getNumber());
                req.setAttribute("client", client);
                req.getRequestDispatcher("/client.jsp").forward(req, resp);
                break;
            case "unblockByOperator":
                contractService.unblockByOperator(contract);
                client = clientService.findClientByNumber(contract.getNumber());
                req.setAttribute("client", client);
                req.getRequestDispatcher("/client.jsp").forward(req, resp);
                break;
            case "blockByClient":
                contractService.blockByClient(contract);
                client = clientService.findClientByNumber(contract.getNumber());
                req.setAttribute("client", client);
                req.getRequestDispatcher("/client.jsp").forward(req, resp);
                break;
            case "unblockByClient":
                contractService.unblockByClient(contract);
                client = clientService.findClientByNumber(contract.getNumber());
                req.setAttribute("client", client);
                req.getRequestDispatcher("/client.jsp").forward(req, resp);
                break;
            case "changeTariff":
                List<Tariff> tariffs = tariffService.getAllTariffs();
                req.setAttribute("contract", contract);
                req.setAttribute("tariffs", tariffs);
                req.getRequestDispatcher("/chooseTariff.jsp").forward(req, resp);
                break;
            case "setNewTariff":
                long tariffId = Long.valueOf(req.getParameter("tariffId"));
                contract.setTariff(tariffService.loadTariff(tariffId));
                contract.getOptions().clear();
                List<String> optionsId = Arrays.asList(req.getParameterValues("options"));
                long optionId;
                for(String stringId: optionsId) {
                    optionId = Long.valueOf(stringId);
                    contract = contractService.enableOption(contract, optionService.loadOption(optionId));
                }
                contract = contractService.saveOrUpdateContract(contract);
                req.setAttribute("contract", contract);
                req.getRequestDispatcher("/contract.jsp").forward(req, resp);
                break;
            default: break;
        }
    }
}
