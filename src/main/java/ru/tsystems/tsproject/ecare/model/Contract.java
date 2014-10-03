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
    private long number;
    private String tariff;
    private Map<String, Option> options = new HashMap<>();
    private boolean isBlockedByClient = false;
    private boolean isBlockedByOperator = false;

    public Contract() {
    }

    public Contract(long number, String tariff, Map<String, Option> options, boolean isBlockedByClient, boolean isBlockedByOperator) {
        this.number = number;
        this.tariff = tariff;
        this.options = options;
        this.isBlockedByClient = isBlockedByClient;
        this.isBlockedByOperator = isBlockedByOperator;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTariff() {
        return tariff;
    }

    public void setTariff(String tariff) {
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
