package ru.tsystems.tsproject.ecare.entities;

import javax.persistence.*;

/**
 * Created by Selvin
 * on 30.09.2014.
 */

@Entity
@Table(name = "option1")
@NamedQueries(
        {
            @NamedQuery (name = "Option.getAllOptions", query = "SELECT o FROM Option o"),
            @NamedQuery (name = "Option.deleteAllOptions", query="DELETE FROM Option o"),
            @NamedQuery (name = "Option.size", query="SELECT count(o) FROM Option o")
        })
public class Option implements Comparable<Option>{
    @Id
    @Column(name = "option_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "option_title")
    private String title;

    @Column(name = "price")
    private int price;

    @Column(name = "cnct_cost")
    private int costOfConnection;

    @ManyToOne
    @JoinColumn(name = "tariff_id")
    private Tariff tariff;

    public Option() {
    }

    public Option(Tariff tariff, String title, int price, int costOfConnection) {
        this.tariff = tariff;
        this.title = title;
        this.price = price;
        this.costOfConnection = costOfConnection;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Option{" +
                "title='" + title + '\'' +
                ", price=" + price +
                ", costOfConnection=" + costOfConnection +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Option option = (Option) o;

        if (costOfConnection != option.costOfConnection) return false;
        if (price != option.price) return false;
        if (!title.equals(option.title)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + price;
        result = 31 * result + costOfConnection;
        return result;
    }

    @Override
    public int compareTo(Option o) {
        return this.toString().compareTo(o.toString());
    }
}
