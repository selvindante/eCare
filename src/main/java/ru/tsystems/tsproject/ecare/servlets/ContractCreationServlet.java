package ru.tsystems.tsproject.ecare.servlets;

import ru.tsystems.tsproject.ecare.business.ClientBusiness;
import ru.tsystems.tsproject.ecare.business.ContractBusiness;
import ru.tsystems.tsproject.ecare.entities.Client;
import ru.tsystems.tsproject.ecare.entities.Contract;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Selvin
 * on 14.10.2014.
 */
public class ContractCreationServlet extends HttpServlet {
    ClientBusiness clientBusiness = new ClientBusiness();
    ContractBusiness contractBusiness = new ContractBusiness();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long clientId = Long.valueOf(req.getParameter("id"));
        long number = Long.valueOf(req.getParameter("number"));
        Client client = clientBusiness.loadClient(clientId);
        Contract contract = new Contract(client, number, null, false, false);
        contractBusiness.createContract(contract);
        client.addContract(contractBusiness.findContractByNumber(number));
        req.setAttribute("role", "admin");
        req.setAttribute("client", client);
        req.getRequestDispatcher("/client.jsp").forward(req, resp);
    }
}
