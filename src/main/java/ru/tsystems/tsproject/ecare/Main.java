package ru.tsystems.tsproject.ecare;

import ru.tsystems.tsproject.ecare.entities.Client;
import ru.tsystems.tsproject.ecare.storage.AbstractClientDAO;
import ru.tsystems.tsproject.ecare.storage.SqlClientDAO;

import java.util.List;

/**
 * Created by Selvin
 * on 04.10.2014.
 */
public class Main {
    public static void main(String[] args) {
        /*Client cl = new Client();
        cl.setName("Semen");
        cl.setLastname("Semenov");
        cl.setBirthDate(new Date());
        cl.setPassport(8450375849l);
        cl.setAddress("Moscow");
        cl.setEmail("semenov@mail.ru");
        cl.setPassword("prnfjgu");

        System.out.println("Created: " + cl);*/

        AbstractClientDAO st = new SqlClientDAO();

        /*Client cl = st.loadClient(3l);
        cl.setAddress("London");
        st.updateClient(cl);
        st.deleteClient(5l);*/

        List<Client> clients = st.getAll();
        for(Client c: clients) {
            System.out.println(c);
        }
    }
}
