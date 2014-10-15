package ru.tsystems.tsproject.ecare;

import ru.tsystems.tsproject.ecare.service.ClientService;

/**
 * Created by Selvin
 * on 04.10.2014.
 */
public class Main {
    public static void main(String[] args) {

        /*OptionBusiness service = new OptionBusiness();
        Option loaded = service.loadOption(1216l);*/

        ClientService clientService = new ClientService();
        clientService.deleteAllClients();

        /*Contract contract = new Contract(client, 123456l, null, false, false);
        contractBusiness.createContract(contract);*/

        /*TariffBusiness tb = new TariffBusiness();
        Tariff tariff = tb.loadTariff(501l);*/
    }
}
