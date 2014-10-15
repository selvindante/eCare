package ru.tsystems.tsproject.ecare;

import ru.tsystems.tsproject.ecare.business.ClientBusiness;

/**
 * Created by Selvin
 * on 04.10.2014.
 */
public class Main {
    public static void main(String[] args) {

        /*OptionBusiness business = new OptionBusiness();
        Option loaded = business.loadOption(1216l);*/

        ClientBusiness clientBusiness = new ClientBusiness();
        clientBusiness.deleteAllClients();

        /*Contract contract = new Contract(client, 123456l, null, false, false);
        contractBusiness.createContract(contract);*/

        /*TariffBusiness tb = new TariffBusiness();
        Tariff tariff = tb.loadTariff(501l);*/
    }
}
