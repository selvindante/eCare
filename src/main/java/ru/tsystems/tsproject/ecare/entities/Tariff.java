package ru.tsystems.tsproject.ecare.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Selvin
 * on 30.09.2014.
 */

@Entity
@Table(name = "tariff")
@NamedQuery(name = "Tariff.getAll", query = "SELECT t FROM Tariff t")
public class Tariff {
    @Id
    @Column(name = "tariff_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "tariff_title")
    private String title;

    @Column(name = "price")
    private int price;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tariff")
    private List<Option> options;

    public Tariff() {
    }

    public Tariff(String title, int price) {
        this.title = title;
        this.price = price;
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

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "Tariff{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}
