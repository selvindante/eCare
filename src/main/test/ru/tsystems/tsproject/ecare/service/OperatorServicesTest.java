package ru.tsystems.tsproject.ecare.service;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.tsystems.tsproject.ecare.ECareException;
import ru.tsystems.tsproject.ecare.entities.Option;
import ru.tsystems.tsproject.ecare.entities.Tariff;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by Selvin
 * on 08.10.2014.
 */
public class OperatorServicesTest {

    private static ITariffService tariffService = TariffService.getInstance();
    private static IOptionService optionService = OptionService.getInstance();

    private static Tariff TR1, TR2;
    private static Option OP11, OP12, OP21, OP22, OP23;

    private static long tariffsNumber;
    private static long optionsNumber;

    @BeforeClass
    public static void beforeClass() {
        TR1 = new Tariff("Tariff1", 200);
        OP11 = new Option(TR1, "Option11", 3, 200);
        OP12 = new Option(TR1, "Option12", 4, 250);
        TR1.addOption(OP11);
        TR1.addOption(OP12);

        TR2 = new Tariff("Tariff2", 300);
        OP21 = new Option(TR2, "Option21", 3, 150);
        OP22 = new Option(TR2, "Option22", 1, 150);
        OP23 = new Option(TR2, "Option23", 2, 200);
        TR2.addOption(OP21);
        TR2.addOption(OP22);
        TR2.addOption(OP23);
    }

    @Before
    public void before() {
        tariffsNumber = tariffService.getNumberOfTariffs();
        optionsNumber = optionService.getNumberOfOptions();
        TR1 = tariffService.saveOrUpdateTariff(TR1);
        TR2 = tariffService.saveOrUpdateTariff(TR2);
        OP11 = TR1.getOptions().get(0);
        OP12 = TR1.getOptions().get(1);
        OP21 = TR2.getOptions().get(0);
        OP22 = TR2.getOptions().get(1);
        OP23 = TR2.getOptions().get(2);
    }

    @Test
    public void testLoadTariff()  throws Exception  {
        assertEquals(TR1, tariffService.loadTariff(TR1.getId()));
        assertEquals(TR2, tariffService.loadTariff(TR2.getId()));
    }

    @Test
    public void testDeleteTariff() throws Exception {
        tariffService.deleteTariff(TR1.getId());
        assertEquals(tariffsNumber + 1l, tariffService.getNumberOfTariffs());
        tariffService.deleteTariff(TR2.getId());
        assertEquals(tariffsNumber, tariffService.getNumberOfTariffs());
    }

    @Test
    public void testGetAllTariffs() throws Exception {
        Tariff[] tariffs = new Tariff[]{TR1, TR2};
        Arrays.sort(tariffs);
        List<Tariff> loadedTariffs = tariffService.getAllTariffs();
        Collections.sort(loadedTariffs);
        assertArrayEquals(tariffs, loadedTariffs.toArray());
    }

    @Test
    public void testDeleteAllTariffs() throws Exception {
        tariffService.deleteAllTariffs();
        assertEquals(tariffsNumber, tariffService.getNumberOfTariffs());
    }

    @Test(expected = ECareException.class)
    public void testLoadMissedTariff() throws Exception {
        tariffService.loadTariff(-12l);
    }

    @Test(expected = ECareException.class)
    public void testDeleteMissedTariff() throws Exception {
        tariffService.deleteTariff(-12l);
    }

    @Test
    public void testAddNewOptionToTariff() throws Exception {
        Option option = new Option(TR1, "Temporary Option", 100, 100);
        TR1.addOption(option);
        TR1 = tariffService.saveOrUpdateTariff(TR1);
        assertEquals(TR1, tariffService.loadTariff(TR1.getId()));
        TR1.getOptions().remove(option);
        TR1 = tariffService.saveOrUpdateTariff(TR1);
        assertEquals(TR1, tariffService.loadTariff(TR1.getId()));
    }

    @Test
    public void testLoadOption() throws Exception {
        assertEquals(OP11, optionService.loadOption(TR1.getOptions().get(0).getId()));
        assertEquals(OP12, optionService.loadOption(TR1.getOptions().get(1).getId()));
        assertEquals(OP21, optionService.loadOption(TR2.getOptions().get(0).getId()));
        assertEquals(OP22, optionService.loadOption(TR2.getOptions().get(1).getId()));
        assertEquals(OP23, optionService.loadOption(TR2.getOptions().get(2).getId()));
    }

    @Test
    public void testUpdateOption() throws Exception {
        OP21.setTitle("Changed Title");
        optionService.saveOrUpdateOption(OP21);
        assertEquals(OP21, optionService.loadOption(OP21.getId()));
    }

    @Test
    public void testDeleteOption() throws Exception {
        TR1.getOptions().remove(OP11);
        tariffService.saveOrUpdateTariff(TR1);
        optionService.deleteOption(OP11.getId());
        assertEquals(optionsNumber + 5l, optionService.getNumberOfOptions());
        TR1.addOption(OP11);
        TR1 = tariffService.saveOrUpdateTariff(TR1);
    }

    @Test
    public void testGetOptionsForTariff() throws Exception {
        Option[] options2 = new Option[]{OP21, OP22, OP23};
        List<Option> loadedOptions2 = optionService.getAllOptionsForTariff(TR2.getId());
        assertArrayEquals(options2, loadedOptions2.toArray());
    }

    @Test
    public void testDeleteAllOptions() throws Exception {
        optionService.deleteAllOptionsForTariff(TR1.getId());
        optionService.deleteAllOptionsForTariff(TR2.getId());
        assertEquals(optionsNumber, optionService.getNumberOfOptions());
    }

    @Test
    public void testSetDependentOption() throws Exception {
        Option dependentOP11 = new Option(TR1, "Dependent Option 11", 2, 300);
        TR1.addOption(dependentOP11);
        optionService.setDependentOption(OP11, dependentOP11);
        TR1 = tariffService.saveOrUpdateTariff(TR1);
        assertEquals(dependentOP11, OP11.getDependentOptions().get(0));
    }

    @Test
    public void testSetIncompatibleOption() throws Exception {
        Option incompatibleOP11 = new Option(TR1, "Incompatible Option 11", 3, 220);
        TR1.addOption(incompatibleOP11);
        optionService.setIncompatibleOption(OP11, incompatibleOP11);
        TR1 = tariffService.saveOrUpdateTariff(TR1);
        assertEquals(incompatibleOP11, OP11.getIncompatibleOptions().get(1));
    }

    @Test(expected = ECareException.class)
    public void testSetDependentOptionWhichIsIncompatible() throws Exception {
        Option incompatibleOP12 = new Option(TR1, "Incompatible Option 12", 3, 220);
        TR1.addOption(incompatibleOP12);
        optionService.setIncompatibleOption(OP12, incompatibleOP12);
        TR1 = tariffService.saveOrUpdateTariff(TR1);
        optionService.setDependentOption(OP12, incompatibleOP12);
    }

    @Test(expected = ECareException.class)
    public void testLoadMissedOption() throws Exception {
        optionService.loadOption(-12l);
    }

    @Test(expected = ECareException.class)
    public void testDeleteMissedContract() throws Exception {
        optionService.deleteOption(-12l);
    }

    @After
    public void after() {
        if(tariffService.getNumberOfTariffs() > tariffsNumber) {
            tariffService.deleteTariff(TR1.getId());
            tariffService.deleteTariff(TR2.getId());
        }
    }
}
