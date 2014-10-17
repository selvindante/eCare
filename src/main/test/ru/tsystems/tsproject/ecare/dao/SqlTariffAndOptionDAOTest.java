package ru.tsystems.tsproject.ecare.dao;

/**
 * Created by Selvin
 * on 08.10.2014.
 */
public class SqlTariffAndOptionDAOTest {
    /*private static AbstractClientDAO clst = new SqlClientDAO();
    private static AbstractTariffDAO trst = new SqlTariffDAO();
    private static AbstractOptionDAO opst = new SqlOptionDAO();

    private static Tariff TR1, TR2;
    private static Option OP1, OP2, OP3, OP4, OP5;

    @BeforeClass
    public static void beforeClass() {
        TR1 = new Tariff("Tariff1", 200);
        OP1 = new Option(TR1, "Option1", 3, 200);
        OP2 = new Option(TR1, "Option2", 4, 250);
        TR1.addOption(OP1);
        TR1.addOption(OP2);

        TR2 = new Tariff("Tariff2", 300);
        OP3 = new Option(TR2, "Option3", 3, 150);
        OP4 = new Option(TR2, "Option4", 1, 150);
        OP5 = new Option(TR2, "Option5", 2, 200);
        TR2.addOption(OP3);
        TR2.addOption(OP4);
        TR2.addOption(OP5);
    }

    @Before
    public void before() {
        clst.deleteAllClients();
        trst.deleteAll();
        trst.saveOrUpdateTariff(TR1);
        trst.saveOrUpdateTariff(TR2);
    }

    @Test
    public void testLoadTariff()  throws Exception  {
        List<Tariff> tariffs = trst.getAll();
        assertEquals(TR1, trst.loadTariff(tariffs.get(0).getId()));
        assertEquals(TR2, trst.loadTariff(tariffs.get(1).getId()));
    }

    @Test
    public void testUpdateTariff() throws Exception {
        List<Tariff> tariffs = trst.getAll();
        Tariff tr = trst.loadTariff(tariffs.get(0).getId());
        tr.setPrice(100);
        trst.updateTariff(tr);
        assertEquals(tr, trst.loadTariff(tr.getId()));
    }

    @Test
    public void testDeleteTariff() throws Exception {
        List<Tariff> tariffs = trst.getAll();
        trst.deleteTariff(tariffs.get(0).getId());
        assertEquals(1l, trst.size());
        trst.deleteTariff(tariffs.get(1).getId());
        assertEquals(0l, trst.size());
    }

    @Test
    public void testGetAllTariffs() throws Exception {
        Tariff[] tariffs = new Tariff[]{TR1, TR2};
        Arrays.sort(tariffs);
        List<Tariff> loadedTariffs = trst.getAll();
        Collections.sort(loadedTariffs);
        assertArrayEquals(tariffs, loadedTariffs.toArray());
    }

    @Test
    public void testDeleteAllTariffs() throws Exception {
        trst.deleteAll();
        assertEquals(0, trst.size());
    }

    @Test(expected = ECareException.class)
    public void testLoadMissedTariff() throws Exception {
        trst.loadTariff(-12l);
    }

    @Test(expected = ECareException.class)
    public void testDeleteMissedTariff() throws Exception {
        trst.deleteTariff(-12l);
    }

    @Test
    public void testAddNewOptionToTariff() throws Exception {
        List<Tariff> tariffs = trst.getAll();
        Tariff tariff = tariffs.get(0);
        Option option = new Option(tariff, "Temporary Option", 100, 100);
        tariff.addOption(option);
        trst.updateTariff(tariff);
        assertEquals(tariff, trst.loadTariff(tariff.getId()));
    }

    @Test
    public void testLoadOption() throws Exception {
        List<Option> options = opst.getAll();
        assertEquals(OP1, opst.loadOption(options.get(0).getId()));
        assertEquals(OP2, opst.loadOption(options.get(1).getId()));
        assertEquals(OP3, opst.loadOption(options.get(2).getId()));
        assertEquals(OP4, opst.loadOption(options.get(3).getId()));
        assertEquals(OP5, opst.loadOption(options.get(4).getId()));
    }

    @Test
    public void testUpdateOption() throws Exception {
        List<Option> options = opst.getAll();
        Option option = options.get(0);
        option.setTitle("Changed Title");
        opst.updateOption(option);
        assertEquals(option, opst.loadOption(option.getId()));
    }

    *//*@Test
    public void testDeleteOption() throws Exception {
        List<Option> options = opst.getAllClients();
        opst.deleteOption(options.get(0).getId());
        assertEquals(4, opst.size());
        opst.deleteOption(options.get(1).getId());
        assertEquals(3, opst.size());
        opst.deleteOption(options.get(2).getId());
        assertEquals(2, opst.size());
        opst.deleteOption(options.get(3).getId());
        assertEquals(1, opst.size());
        opst.deleteOption(options.get(4).getId());
        assertEquals(0, opst.size());
    }*//*

    @Test
    public void testGetAllOptions() throws Exception {
        Option[] options = new Option[]{OP1, OP2, OP3, OP4, OP5};
        Arrays.sort(options);
        List<Option> loadedOptions = opst.getAll();
        Collections.sort(loadedOptions);
        assertArrayEquals(options, loadedOptions.toArray());
    }

    @Test
    public void testGetOptionsForTariff() throws Exception {
        List<Tariff> tariffs = trst.getAll();
        Option[] options = new Option[]{OP3, OP4, OP5};
        List<Option> loadedOptions = opst.getAllForTariff(tariffs.get(1).getId());
        Collections.sort(loadedOptions);
        assertArrayEquals(options, loadedOptions.toArray());
    }

    @Test
    public void testDeleteAllOptions() throws Exception {
        opst.deleteAll();
        assertEquals(0, opst.size());
    }

    @Test(expected = ECareException.class)
    public void testLoadMissedOption() throws Exception {
        opst.loadOption(-12l);
    }

    @Test(expected = ECareException.class)
    public void testDeleteMissedContract() throws Exception {
        opst.deleteOption(-12l);
    }*/
}
