package ru.tsystems.tsproject.ecare.dao;

/**
 * Created by Selvin
 * on 07.10.2014.
 */
public class SqlClientAndContractDAOTest {
    /*private static AbstractClientDAO clst = new SqlClientDAO();
    private static AbstractContractDAO cnst = new SqlContractDAO();
    private static AbstractTariffDAO trst = new SqlTariffDAO();
    private static AbstractOptionDAO opst = new SqlOptionDAO();

    private static Client CL1, CL2, CL3;
    private static Contract CN1, CN2;
    private static Tariff TR;
    private static Option OP1, OP2;

    @BeforeClass
    public static void beforeClass() {
        CL1 = new Client("Ivan", null, null, 9234132135l, "SPB", "ivanov@mail.ru", "password", "client");

        CL2 = new Client("Semen", "Semenov", new Date(), 98274560923l, "Moscow", "semenov@mail.ru", "Qwerty123", "client");
        CN1 = new Contract(CL2, 892345678l, null, false, false);
        CL2.addContract(CN1);

        TR = new Tariff("Tariff1", 200);
        OP1 = new Option(TR, "Option1", 3, 200);
        OP2 = new Option(TR, "Option2", 4, 250);
        TR.addOption(OP1);
        TR.addOption(OP2);
        trst.saveOrUpdateTariff(TR);

        CL3 = new Client("Petr", "Petrov", new Date(), 9582450345l, "Sankt-Peterburg", "petrov@mail.ru", "petrov51spb", "client");
        List<Tariff> tariffs = trst.getAll();
        CN2 = new Contract(CL3, 89652345090l, trst.loadTariff(tariffs.get(0).getId()), false, false);
        List<Option> options = opst.getAll();
        CN2.addOption(opst.loadOption(options.get(0).getId()));
        CN2.addOption(opst.loadOption(options.get(1).getId()));
        CL3.addContract(CN2);
    }

    @Before
    public void before() {
        clst.deleteAllClients();
        clst.saveOrUpdateClient(CL1);
        clst.saveOrUpdateClient(CL2);
        clst.saveOrUpdateClient(CL3);
    }

    @Test
    public void testLoadClient()  throws Exception  {
        List<Client> clients = clst.getAllClients();
        assertEquals(CL1, clst.loadClient(clients.get(0).getId()));
        assertEquals(CL2, clst.loadClient(clients.get(1).getId()));
        assertEquals(CL3, clst.loadClient(clients.get(2).getId()));
    }

    @Test
    public void testUpdateClient() throws Exception {
        List<Client> clients = clst.getAllClients();
        Client client = clst.loadClient(clients.get(0).getId());
        client.setLastname("Ivanov");
        client.setBirthDate(new Date());
        clst.updateClient(client);
        assertEquals(client, clst.loadClient(client.getId()));
    }

    @Test
    public void testDeleteClient() throws Exception {
        List<Client> clients = clst.getAllClients();
        clst.deleteClient(clients.get(0).getId());
        assertEquals(2l, clst.size());
        clst.deleteClient(clients.get(1).getId());
        assertEquals(1l, clst.size());
        clst.deleteClient(clients.get(2).getId());
        assertEquals(0l, clst.size());
    }

    @Test
    public void testGetAllClients() throws Exception {
        Client[] clients = new Client[]{CL1, CL2, CL3};
        Arrays.sort(clients);
        List<Client> loadedClients = clst.getAllClients();
        Collections.sort(loadedClients);
        assertArrayEquals(clients, loadedClients.toArray());
    }

    @Test
    public void testDeleteAllClients() throws Exception {
        clst.deleteAllClients();
        assertEquals(0, clst.size());
    }

    @Test(expected = ECareException.class)
    public void testLoadMissedClient() throws Exception {
        clst.loadClient(-12l);
    }

    @Test(expected = ECareException.class)
    public void testDeleteMissedClient() throws Exception {
        clst.deleteClient(-12l);
    }

    @Test
    public void testAddNewContractToClient() throws Exception {
        List<Client> clients = clst.getAllClients();
        Client client = clst.loadClient(clients.get(0).getId());
        List<Tariff> tariffs = trst.getAll();
        Contract contract = new Contract(client, 89279821345l, tariffs.get(0), false, false);
        List<Option> options = opst.getAll();
        contract.addOption(opst.loadOption(options.get(0).getId()));
        contract.addOption(opst.loadOption(options.get(1).getId()));
        client.addContract(contract);
        clst.updateClient(client);
        assertEquals(client, clst.loadClient(client.getId()));
    }

    @Test
    public void testLoadContract() throws Exception {
        List<Contract> contracts = cnst.getAll();
        assertEquals(CN1, cnst.loadContract(contracts.get(0).getId()));
        assertEquals(CN2, cnst.loadContract(contracts.get(1).getId()));
    }

    @Test
    public void testUpdateContract() throws Exception {
        List<Contract> contracts = cnst.getAll();
        Contract cn = contracts.get(0);
        cn.setBlockByClient(true);
        cnst.updateContract(cn);
        assertEquals(cn, cnst.loadContract(cn.getId()));
    }

    *//*@Test
    public void testDeleteContract() throws Exception {
        List<Contract> contracts = cnst.getAllClients();
        cnst.deleteContract(contracts.get(0).getId());
        assertEquals(1, cnst.size());
        cnst.deleteContract(contracts.get(1).getId());
        assertEquals(0, cnst.size());
    }*//*

    @Test
    public void testGetAllContracts() throws Exception {
        Contract[] contracts = new Contract[]{CN1, CN2};
        Arrays.sort(contracts);
        List<Contract> loadedContracts = cnst.getAll();
        Collections.sort(loadedContracts);
        assertArrayEquals(contracts, loadedContracts.toArray());
    }

    @Test
    public void testGetContractsForClient() throws Exception {
        List<Client> clients = clst.getAllClients();
        Contract[] contracts = new Contract[]{CN1};
        List<Contract> loadedContracts = cnst.getAllForClient(clients.get(1).getId());
        Collections.sort(loadedContracts);
        assertArrayEquals(contracts, loadedContracts.toArray());
    }

    @Test
    public void testDeleteAllContracts() throws Exception {
        cnst.deleteAll();
        assertEquals(0, cnst.size());
    }

    @Test(expected = ECareException.class)
    public void testLoadMissedContract() throws Exception {
        cnst.loadContract(-12l);
    }

    @Test(expected = ECareException.class)
    public void testDeleteMissedContract() throws Exception {
        cnst.deleteContract(-12l);
    }*/
}
