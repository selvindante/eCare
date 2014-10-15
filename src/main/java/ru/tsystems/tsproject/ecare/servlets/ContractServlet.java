package ru.tsystems.tsproject.ecare.servlets;

import ru.tsystems.tsproject.ecare.business.ClientBusiness;
import ru.tsystems.tsproject.ecare.business.ContractBusiness;
import ru.tsystems.tsproject.ecare.business.TariffBusiness;
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
    ContractBusiness contractBusiness = new ContractBusiness();
    ClientBusiness clientBusiness = new ClientBusiness();
    TariffBusiness tariffBusiness = new TariffBusiness();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long contractId = Long.valueOf(req.getParameter("id"));
        String action = req.getParameter("action");
        Contract contract = contractBusiness.loadContract(contractId);
        Client client = null;
        switch(action) {
            case "viewContract":
                req.setAttribute("contract", contract);
                req.getRequestDispatcher("/contract.jsp").forward(req, resp);
                break;
            case "deleteContract":
                contractBusiness.deleteContract(contractId);
                client = clientBusiness.findClientByNumber(contract.getNumber());
                req.setAttribute("role", "admin");
                req.setAttribute("client", client);
                req.getRequestDispatcher("/client.jsp").forward(req, resp);
                break;
            case "blockByOperator":
                contractBusiness.blockByOperator(contract);
                client = clientBusiness.findClientByNumber(contract.getNumber());
                req.setAttribute("role", "admin");
                req.setAttribute("client", client);
                req.getRequestDispatcher("/client.jsp").forward(req, resp);
                break;
            case "unblockByOperator":
                contractBusiness.unblockByOperator(contract);
                client = clientBusiness.findClientByNumber(contract.getNumber());
                req.setAttribute("role", "admin");
                req.setAttribute("client", client);
                req.getRequestDispatcher("/client.jsp").forward(req, resp);
                break;
            case "blockByClient":
                contractBusiness.blockByClient(contract);
                client = clientBusiness.findClientByNumber(contract.getNumber());
                req.setAttribute("role", "client");
                req.setAttribute("client", client);
                req.getRequestDispatcher("/client.jsp").forward(req, resp);
                break;
            case "unblockByClient":
                contractBusiness.unblockByClient(contract);
                client = clientBusiness.findClientByNumber(contract.getNumber());
                req.setAttribute("role", "client");
                req.setAttribute("client", client);
                req.getRequestDispatcher("/client.jsp").forward(req, resp);
                break;
            case "changeTariff":
                List<Tariff> tariffs = tariffBusiness.getAllTariffs();
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
