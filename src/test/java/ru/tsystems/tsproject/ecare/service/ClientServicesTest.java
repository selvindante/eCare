package ru.tsystems.tsproject.ecare.service;

import org.junit.*;
import ru.tsystems.tsproject.ecare.ECareException;
import ru.tsystems.tsproject.ecare.entities.Client;
import ru.tsystems.tsproject.ecare.entities.Contract;
import ru.tsystems.tsproject.ecare.entities.Option;
import ru.tsystems.tsproject.ecare.entities.Tariff;

import java.util.Date;

/**
 * Created by Selvin
 * on 07.10.2014.
 */
public class ClientServicesTest {
    private static IClientService clientService = ClientService.getInstance();
    private static IContractService contractService = ContractService.getInstance();
    private static ITariffService tariffService = TariffService.getInstance();
    private static IOptionService optionService = OptionService.getInstance();

    private static Client CL1, CL2, CL3;
    private static Contract CN21, CN31;
    private static Tariff TR1;
    private static Option OP11, OP12, OP13, OP14, OP15;

    private static long clientsNumber;
    private static long contractsNumber;

    @Before
    public void before() {
        clientsNumber = clientService.getNumberOfClients();
        contractsNumber = contractService.getNumberOfContracts();

        TR1 = new Tariff("tTariff1", 300);
        TR1 = tariffService.saveOrUpdateTariff(TR1);

        OP11 = new Option(TR1, "tOption11", 5, 120);
        OP11 = optionService.saveOrUpdateOption(OP11);

        OP12 = new Option(TR1, "tOption12", 4, 100);
        OP12 = optionService.saveOrUpdateOption(OP12);

        OP13 = new Option(TR1, "tOption13", 5, 50);
        OP13 = optionService.saveOrUpdateOption(OP13);

        OP14 = new Option(TR1, "tOption14", 10, 100);
        OP14 = optionService.saveOrUpdateOption(OP14);

        OP15 = new Option(TR1, "tOption15", 2, 110);
        OP15 = optionService.saveOrUpdateOption(OP15);

        TR1.addOption(OP11);
        TR1.addOption(OP12);
        TR1.addOption(OP13);
        TR1.addOption(OP14);
        TR1.addOption(OP15);

        OP11.addDependentOption(OP12);
        OP11.addIncompatibleOption(OP14);

        TR1 = tariffService.saveOrUpdateTariff(TR1);

        CL1 = new Client("Ivan", null, null, 9234132135l, "SPB", "ivanov@mail.ru", "password", "client", 1000);
        CL1 = clientService.saveOrUpdateClient(CL1);

        CL2 = new Client("Semen", "Semenov", new Date(), 98274560923l, "Moscow", "semenov@mail.ru", "Qwerty123", "client", 1000);
        CL2 = clientService.saveOrUpdateClient(CL2);

        CN21 = new Contract(CL2, 12345643l, null, false, false);

        CL3 = new Client("Petr", "Petrov", new Date(), 9582450345l, "Sankt-Peterburg", "petrov@mail.ru", "petrov51spb", "client", 2000);
        CL3 = clientService.saveOrUpdateClient(CL3);

        CN31 = new Contract(CL3, 89652345090l, TR1, false, false);
        CN31 = contractService.saveOrUpdateContract(CN31);
        CN31 = contractService.enableOption(CN31, OP11);
        CN31 = contractService.enableOption(CN31, OP13);
        CL3.addContract(CN31);
        CL3 = clientService.saveOrUpdateClient(CL3);
    }

    @Test
    public void testLoginClient() throws Exception {
        Client client = clientService.findClient(CL2.getEmail(), CL2.getPassword());
        Assert.assertEquals(CL2, client);
    }

    @Test
    public void testFindClientByNumber() throws Exception {
        Client client = clientService.findClientByNumber(CN31.getNumber());
        Assert.assertEquals(CL3, client);
    }

    @Test
    public void testLoadClient()  throws Exception  {
        Assert.assertEquals(CL1, clientService.loadClient(CL1.getId()));
        Assert.assertEquals(CL2, clientService.loadClient(CL2.getId()));
        Assert.assertEquals(CL3, clientService.loadClient(CL3.getId()));
    }

    @Test
    public void testUpdateClient() throws Exception {
        CL1 = clientService.loadClient(CL1.getId());
        CL1.setLastname("Ivanov");
        CL1.setBirthDate(new Date());
        CL1 = clientService.saveOrUpdateClient(CL1);
        Assert.assertEquals(CL1, clientService.loadClient(CL1.getId()));
    }

    @Test
    public void testDeleteClient() throws Exception {
        clientService.deleteClient(CL1.getId());
        Assert.assertEquals(clientsNumber + 2l, clientService.getNumberOfClients());
        clientService.deleteClient(CL2.getId());
        Assert.assertEquals(clientsNumber + 1l, clientService.getNumberOfClients());
        clientService.deleteClient(CL3.getId());
        Assert.assertEquals(clientsNumber, clientService.getNumberOfClients());
        tariffService.deleteTariff(TR1.getId());
    }

    @Test(expected = ECareException.class)
    public void testLoadMissedClient() throws Exception {
        clientService.loadClient(-12l);
    }

    @Test(expected = ECareException.class)
    public void testDeleteMissedClient() throws Exception {
        clientService.deleteClient(-12l);
    }

    @Test
    public void testAddNewContractToClient() throws Exception {
        CN21 = new Contract(CL2, 12345643l, null, false, false);
        CL2.addContract(CN21);
        CL2 = clientService.saveOrUpdateClient(CL2);
        CN21 = CL2.getContracts().get(0);
        Assert.assertEquals(CL2, clientService.loadClient(CL2.getId()));
    }

    @Test
    public void testLoadContract() throws Exception {
        Assert.assertEquals(CN31, contractService.loadContract(CN31.getId()));
    }

    @Test
    public void testDeleteContract() throws Exception {
        contractService.deleteContract(CN31.getId());
        Assert.assertEquals(contractsNumber + 1l, contractService.getNumberOfContracts());
    }

    @Test(expected = ECareException.class)
    public void testLoadMissedContract() throws Exception {
        contractService.loadContract(-12l);
    }

    @Test(expected = ECareException.class)
    public void testDeleteMissedContract() throws Exception {
        contractService.deleteContract(-12l);
    }

    @After
    public void after() {
        if(clientService.getNumberOfClients() > clientsNumber) {
            if(clientService.existLogin(CL1.getEmail())) clientService.deleteClient(CL1.getId());
            if(clientService.existLogin(CL2.getEmail())) clientService.deleteClient(CL2.getId());
            if(clientService.existLogin(CL3.getEmail())) clientService.deleteClient(CL3.getId());
            tariffService.deleteTariff(TR1.getId());
        }
    }
}
