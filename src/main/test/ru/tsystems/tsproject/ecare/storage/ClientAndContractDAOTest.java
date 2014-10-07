package ru.tsystems.tsproject.ecare.storage;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.tsystems.tsproject.ecare.entities.Client;
import ru.tsystems.tsproject.ecare.entities.Contract;
import ru.tsystems.tsproject.ecare.entities.Option;
import ru.tsystems.tsproject.ecare.entities.Tariff;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Selvin
 * on 07.10.2014.
 */
public class ClientAndContractDAOTest {
    static AbstractClientDAO clst = new SqlClientDAO();
    static AbstractContractDAO cnst = new SqlContractDAO();
    static AbstractTariffDAO trst = new SqlTariffDAO();
    static AbstractOptionDAO opst = new SqlOptionDAO();

    private static Client CL1, CL2, CL3;
    private static Contract CN1, CN2;

    @BeforeClass
    public static void beforeClass() {
        CL1 = new Client("Ivan", null, null, 9234132135l, "SPB", "ivanov@mail.ru", "password");

        CL2 = new Client("Semen", "Semenov", new Date(), 98274560923l, "Moscow", "semenov@mail.ru", "Qwerty123");
        CN1 = new Contract(CL2, 892345678l, null, false, false);
        CL2.addContract(CN1);

        CL3 = new Client("Petr", "Petrov", new Date(), 9582450345l, "Sankt-Peterburg", "petrov@mail.ru", "petrov51spb");
        List<Tariff> tariffs = trst.getAll();
        CN2 = new Contract(CL3, 89652345090l, trst.loadTariff(tariffs.get(0).getId()), false, false);
        List<Option> options = opst.getAll();
        CN2.addOption(opst.loadOption(options.get(0).getId()));
        CN2.addOption(opst.loadOption(options.get(1).getId()));
        CL3.addContract(CN2);
    }

    @Before
    public void before() {
        clst.deleteAll();
        clst.createClient(CL1);
        clst.createClient(CL2);
        clst.createClient(CL3);
    }

    @Test
    public void testLoad()  throws Exception  {
        List<Client> clients = clst.getAll();
        assertEquals(CL1, clst.loadClient(clients.get(0).getId()));
        assertEquals(CL2, clst.loadClient(clients.get(1).getId()));
        assertEquals(CL3, clst.loadClient(clients.get(2).getId()));
    }

    @Test
    public void testUpdate() throws Exception {
        List<Client> clients = clst.getAll();
        Client client = clst.loadClient(clients.get(0).getId());
        client.setLastname("Ivanov");
        client.setBirthDate(new Date());
        clst.updateClient(client);
        assertEquals(client, clst.loadClient(client.getId()));
    }

    /*@Test
    public void testDelete() throws Exception {
        List<Client> clients = clst.getAll();
        clst.deleteClient(clients.get(0).getId());
        assertEquals(2l, clst.size());
        clst.deleteClient(clients.get(1).getId());
        assertEquals(1l, clst.size());
        clst.deleteClient(clients.get(2).getId());
        assertEquals(0l, clst.size());
    }*/
}
