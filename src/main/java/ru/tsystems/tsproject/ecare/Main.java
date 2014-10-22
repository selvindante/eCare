package ru.tsystems.tsproject.ecare;

import ru.tsystems.tsproject.ecare.entities.Tariff;
import ru.tsystems.tsproject.ecare.service.ITariffService;
import ru.tsystems.tsproject.ecare.service.TariffService;

/**
 * Created by Selvin
 * on 04.10.2014.
 */
public class Main {
    public static void main(String[] args) {
        ITariffService ts = TariffService.getInstance();
        Tariff tr = ts.loadTariff(1782l);
    }
}
