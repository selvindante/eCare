package ru.tsystems.tsproject.ecare;

import ru.tsystems.tsproject.ecare.storage.AbstractClientDAO;
import ru.tsystems.tsproject.ecare.storage.SqlClientDAO;

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

        /*Client cl = new Client("Semen", "Semenov", new Date(), 9505601234l, "SPB", "semenov@mail.ru", "qwerty321");

        Contract cn = new Contract(cl, 9650327493l, null, false, false);
        cl.addContract(cn);

        AbstractClientDAO clst = new SqlClientDAO();
        clst.createClient(cl);*/

        AbstractClientDAO clst = new SqlClientDAO();
        long er = clst.size();

        /*AbstractTariffDAO trst = new SqlTariffDAO();
        Tariff tr = trst.loadTariff(33);*/
    }
}
