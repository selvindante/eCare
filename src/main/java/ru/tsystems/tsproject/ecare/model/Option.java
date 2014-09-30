package ru.tsystems.tsproject.ecare.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Selvin
 * on 30.09.2014.
 */

@Entity
public class Option {
    @Id
    private String title;
    private int price;
    private int costOfConnection;

    public Option() {
    }

    public Option(String title, int price, int costOfConnection) {
        this.title = title;
        this.price = price;
        this.costOfConnection = costOfConnection;
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

    public int getCostOfConnection() {
        return costOfConnection;
    }

    public void setCostOfConnection(int costOfConnection) {
        this.costOfConnection = costOfConnection;
    }
}
