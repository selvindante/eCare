package ru.tsystems.tsproject.ecare;

import ru.tsystems.tsproject.ecare.business.ClientBusiness;
import ru.tsystems.tsproject.ecare.entities.Client;

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

        ClientBusiness cb = new ClientBusiness();
        Client cl = cb.findClientByNumber(892345678l);

        /*AbstractTariffDAO trst = new SqlTariffDAO();
        Tariff tr = trst.loadTariff(33);*/
    }
}
