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
public class Tariff {
    @Id
    private String title;
    private int price;
    private Map<String, Option> possibleOptions = new HashMap<>();

    public Tariff() {
    }

    public Tariff(String title, int price, Map<String, Option> possibleOptions) {
        this.title = title;
        this.price = price;
        this.possibleOptions = possibleOptions;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
