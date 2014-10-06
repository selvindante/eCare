package ru.tsystems.tsproject.ecare;

import ru.tsystems.tsproject.ecare.entities.Client;
import ru.tsystems.tsproject.ecare.entities.Contract;
import ru.tsystems.tsproject.ecare.storage.AbstractClientDAO;
import ru.tsystems.tsproject.ecare.storage.SqlClientDAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Selvin
 * on 04.10.2014.
 */
public class Main {
    public static void main(String[] args) {

        Client cl = new Client("Semen", "Semenov", new Date(), 9505601234l, "SPB", "semenov@mail.ru", "qwerty321");
        System.out.println("Created: " + cl);

        List<Contract> contracts = new ArrayList<>();
        Contract cn = new Contract(cl, 9650327493l, null, false, false);
        contracts.add(cn);
        cl.setContracts(contracts);

        AbstractClientDAO clst = new SqlClientDAO();
        clst.createClient(cl);

        /*AbstractContractDAO cnst = new SqlContractDAO();
        cnst.createContract(cn);*/

        /*Client cl = st.loadClient(3l);
        cl.setAddress("London");
        st.updateClient(cl);
        st.deleteClient(5l);*/

        List<Client> clients = clst.getAll();
        for(Client c: clients) {
            System.out.println(c);
        }
    }
}
