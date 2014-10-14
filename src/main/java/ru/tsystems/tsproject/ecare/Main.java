package ru.tsystems.tsproject.ecare;

import ru.tsystems.tsproject.ecare.business.TariffBusiness;
import ru.tsystems.tsproject.ecare.entities.Tariff;

/**
 * Created by Selvin
 * on 04.10.2014.
 */
public class Main {
    public static void main(String[] args) {

        /*AbstractOptionDAO opst = new SqlOptionDAO();
        List<Option> options = new ArrayList<>();
        options.add(opst.loadOption(38));
        options.add(opst.loadOption(39));

        AbstractTariffDAO trst = new SqlTariffDAO();*/

        /*Client cl = new Client("Semen", "Semenov", new Date(), 9505601234l, "SPB", "semenov@mail.ru", "qwerty321");*/

        /*ClientBusiness cb = new ClientBusiness();
        Client cl = cb.findClientByNumber(892345678l);*/

        /*AbstractTariffDAO trst = new SqlTariffDAO();
        Tariff tr = trst.loadTariff(33);*/

        /*Option op1 = new Option(null, "Test1", 400, 200);
        Option op2 = new Option(null, "Test2", 400, 200);
        Option op3 = new Option(null, "Test3", 400, 200);*/

        /*op1.addDependentOption(op2);
        op1.addIncompatibleOption(op3);*/

        /*OptionBusiness business = new OptionBusiness();
        Option loaded = business.loadOption(1216l);*/

        /*ClientBusiness clientBusiness = new ClientBusiness();
        ContractBusiness contractBusiness = new ContractBusiness();

        Client client = clientBusiness.findClient("testcl@mail.ru", "123456");*/

        /*Contract contract = new Contract(client, 123456l, null, false, false);
        contractBusiness.createContract(contract);*/

        TariffBusiness tb = new TariffBusiness();
        Tariff tariff = tb.loadTariff(501l);
    }
}
