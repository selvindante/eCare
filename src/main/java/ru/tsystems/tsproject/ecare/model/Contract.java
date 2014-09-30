package ru.tsystems.tsproject.ecare.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Selvin
 * on 30.09.2014.
 */

@Entity
public class Contract {
    private Client client;
    @Id
    private int number;
    private Tariff tariff;
    private Map<String, Option> options = new HashMap<>();
    private boolean isBlockedByClient = false;
    private boolean isBlockedByOperator = false;

    public Contract() {
    }

    public Contract(int number, Tariff tariff, Map<String, Option> options, boolean isBlockedByClient, boolean isBlockedByOperator) {
        this.number = number;
        this.tariff = tariff;
        this.options = options;
        this.isBlockedByClient = isBlockedByClient;
        this.isBlockedByOperator = isBlockedByOperator;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    public boolean isBlockedByClient() {
        return isBlockedByClient;
    }

    public void setBlockByClient(boolean block) {
        if(!block && isBlockedByOperator) return;
        isBlockedByClient = block;
    }

    public boolean isBlockedByOperator() {
        return isBlockedByOperator;
    }

    public void setBlockByOperator(boolean block) {
        isBlockedByOperator = block;
    }
}
