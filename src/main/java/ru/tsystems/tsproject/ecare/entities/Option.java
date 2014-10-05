package ru.tsystems.tsproject.ecare.entities;

import javax.persistence.*;

/**
 * Created by Selvin
 * on 30.09.2014.
 */

@Entity
@Table(name = "option")
public class Option {
    @Id
    @Column(name = "option_id")
    private long id;

    @Column(name = "option_title")
    private String title;

    @Column(name = "price")
    private int price;

    @Column(name = "cost_of_connection")
    private int costOfConnection;

    @ManyToOne
    @JoinColumn(name = "tariff_id")
    private Tariff tariff;

    public Option() {
    }

    public Option(long id, String title, int price, int costOfConnection) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.costOfConnection = costOfConnection;
    }

    public long getId() {
        return id;
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
