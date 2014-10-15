package ru.tsystems.tsproject.ecare.servlets;

import ru.tsystems.tsproject.ecare.service.ClientService;
import ru.tsystems.tsproject.ecare.service.ContractService;
import ru.tsystems.tsproject.ecare.service.TariffService;
import ru.tsystems.tsproject.ecare.entities.Client;
import ru.tsystems.tsproject.ecare.entities.Contract;
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
public class ContractServlet extends HttpServlet {
    ContractService contractService = new ContractService();
    ClientService clientService = new ClientService();
    TariffService tariffService = new TariffService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long contractId = Long.valueOf(req.getParameter("id"));
        String action = req.getParameter("action");
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
                req.setAttribute("role", "admin");
                req.setAttribute("client", client);
                req.getRequestDispatcher("/client.jsp").forward(req, resp);
                break;
            case "blockByOperator":
                contractService.blockByOperator(contract);
                client = clientService.findClientByNumber(contract.getNumber());
                req.setAttribute("role", "admin");
                req.setAttribute("client", client);
                req.getRequestDispatcher("/client.jsp").forward(req, resp);
                break;
            case "unblockByOperator":
                contractService.unblockByOperator(contract);
                client = clientService.findClientByNumber(contract.getNumber());
                req.setAttribute("role", "admin");
                req.setAttribute("client", client);
                req.getRequestDispatcher("/client.jsp").forward(req, resp);
                break;
            case "blockByClient":
                contractService.blockByClient(contract);
                client = clientService.findClientByNumber(contract.getNumber());
                req.setAttribute("role", "client");
                req.setAttribute("client", client);
                req.getRequestDispatcher("/client.jsp").forward(req, resp);
                break;
            case "unblockByClient":
                contractService.unblockByClient(contract);
                client = clientService.findClientByNumber(contract.getNumber());
                req.setAttribute("role", "client");
                req.setAttribute("client", client);
                req.getRequestDispatcher("/client.jsp").forward(req, resp);
                break;
            case "changeTariff":
                List<Tariff> tariffs = tariffService.getAllTariffs();
                req.setAttribute("contract", contract);
                req.setAttribute("tariffs", tariffs);
                req.getRequestDispatcher("/chooseTariff.jsp").forward(req, resp);
                break;
            default: break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
