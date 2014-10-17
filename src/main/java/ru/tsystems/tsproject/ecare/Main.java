package ru.tsystems.tsproject.ecare;

import ru.tsystems.tsproject.ecare.service.IOptionService;
import ru.tsystems.tsproject.ecare.service.OptionService;

/**
 * Created by Selvin
 * on 04.10.2014.
 */
public class Main {
    public static void main(String[] args) {
        IOptionService ops = OptionService.getInstance();
        ops.deleteAllOptionsForTariff(24234324l);
    }
}
