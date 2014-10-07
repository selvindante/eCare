package ru.tsystems.tsproject.ecare.storage;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.tsystems.tsproject.ecare.entities.Client;
import ru.tsystems.tsproject.ecare.entities.Contract;
import ru.tsystems.tsproject.ecare.entities.Option;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Selvin
 * on 07.10.2014.
 */
public class ClientAndContractDAOTest {
    static AbstractClientDAO clst;
    static AbstractContractDAO cnst;
    static AbstractTariffDAO trst;
    static AbstractOptionDAO opst;

    private static Client CL1, CL2, CL3;
    private static Contract CN1, CN2;

    @BeforeClass
    public static void beforeClass() {
        CL1 = new Client("Ivan", null, null, 9234132135l, "SPB", "ivanov@mail.ru", "password");

        CL2 = new Client("Semen", "Semenov", new Date(), 98274560923l, "Moscow", "semenov@mail.ru", "Qwerty123");
        CN1 = new Contract(CL2, 892345678l, null, false, false);

        CL3 = new Client("Petr", "Petrov", new Date(), 9582450345l, "Sankt-Peterburg", "petrov@mail.ru", "petrov51spb");
        CN2 = new Contract(CL3, 89652345090l, trst.loadTariff(33), false, false);
        List<Option> options = new ArrayList<>();
        options.add(opst.loadOption(37));
        options.add(opst.loadOption(38));
        CN2.setOptions(options);
        List<Contract> contracts = new ArrayList<>();
        contracts.add(CN2);
        CL3.setContracts(contracts);
    }

    @Before
    public void before() {
        clst.deleteAll();
        clst.createClient(CL1);
        clst.createClient(CL2);
        clst.createClient(CL3);
    }

    @Test
    public void testLoad() {
        assertEquals(CL1, clst.loadClient(CL1.getId()));
    }
}
