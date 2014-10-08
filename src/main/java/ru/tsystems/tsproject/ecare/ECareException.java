package ru.tsystems.tsproject.ecare;

import ru.tsystems.tsproject.ecare.entities.Client;
import ru.tsystems.tsproject.ecare.entities.Contract;
import ru.tsystems.tsproject.ecare.entities.Option;
import ru.tsystems.tsproject.ecare.entities.Tariff;

/**
 * Created by Selvin
 * on 08.10.2014.
 */
public class ECareException extends RuntimeException {
    private long id;
    private Client client;
    private Contract contract;
    private Tariff tariff;
    private Option option;

    public ECareException(String message) {
        super(message);
    }

    public ECareException(String message, Throwable e) {
        super(message, e);
    }

    public ECareException(String message, long id) {
        super(message);
        this.id = id;
    }

    public ECareException(String message, Client client) {
        super(message);
        this.client = client;
    }

    public ECareException(String message, Client client, Throwable e) {
        super(message, e);
        this.client = client;
    }

    public ECareException(String message, Contract contract) {
        super(message);
        this.contract = contract;
    }

    public ECareException(String message, Contract contract, Throwable e) {
        super(message, e);
        this.contract = contract;
    }

    public ECareException(String message, Tariff tariff) {
        super(message);
        this.tariff = tariff;
    }

    public ECareException(String message, Tariff tariff, Throwable e) {
        super(message, e);
        this.tariff = tariff;
    }

    public ECareException(String message, Option option) {
        super(message);
        this.option = option;
    }

    public ECareException(String message, Option option, Throwable e) {
        super(message, e);
        this.option = option;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    public void setOption(Option option) {
        this.option = option;
    }
}
