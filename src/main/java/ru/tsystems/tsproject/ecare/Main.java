package ru.tsystems.tsproject.ecare;

import ru.tsystems.tsproject.ecare.entities.Tariff;
import ru.tsystems.tsproject.ecare.storage.AbstractTariffDAO;
import ru.tsystems.tsproject.ecare.storage.SqlTariffDAO;

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

        AbstractTariffDAO trst = new SqlTariffDAO();

        Client cl = new Client("Semen", "Semenov", new Date(), 9505601234l, "SPB", "semenov@mail.ru", "qwerty321");

        List<Contract> contracts = new ArrayList<>();
        Contract cn = new Contract(cl, 9650327493l, trst.loadTariff(33), false, false);
        cn.setOptions(options);
        contracts.add(cn);
        cl.setContracts(contracts);

        AbstractClientDAO clst = new SqlClientDAO();
        clst.createClient(cl);*/

        /*AbstractClientDAO clst = new SqlClientDAO();
        clst.deleteAll();*/

        AbstractTariffDAO trst = new SqlTariffDAO();
        Tariff tr = trst.loadTariff(33);
    }
}
