package ru.tsystems.tsproject.ecare;

import ru.tsystems.tsproject.ecare.entities.Contract;
import ru.tsystems.tsproject.ecare.storage.AbstractContractDAO;
import ru.tsystems.tsproject.ecare.storage.SqlContractDAO;

import java.util.List;

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

        AbstractContractDAO cnst = new SqlContractDAO();
        List<Contract> contracts = cnst.getAll();
        cnst.deleteContract(contracts.get(0).getId());
        long i = cnst.size();

        /*AbstractTariffDAO trst = new SqlTariffDAO();
        Tariff tr = trst.loadTariff(33);*/
    }
}
