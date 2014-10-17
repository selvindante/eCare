package ru.tsystems.tsproject.ecare;

import ru.tsystems.tsproject.ecare.entities.Client;
import ru.tsystems.tsproject.ecare.entities.Tariff;
import ru.tsystems.tsproject.ecare.service.ClientService;
import ru.tsystems.tsproject.ecare.service.IClientService;
import ru.tsystems.tsproject.ecare.service.ITariffService;
import ru.tsystems.tsproject.ecare.service.TariffService;

/**
 * Created by Selvin
 * on 04.10.2014.
 */
public class Main {
    public static void main(String[] args) {
        IClientService cls = ClientService.getInstance();
        Client cl = cls.loadClient(1396l);

        ITariffService trs = TariffService.getInstance();
        Tariff tr = trs.loadTariff(502l);
    }
}
