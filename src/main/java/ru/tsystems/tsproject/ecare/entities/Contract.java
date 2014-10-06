package ru.tsystems.tsproject.ecare.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Selvin
 * on 30.09.2014.
 */

@Entity
@Table(name = "contract")
@NamedQuery(name = "Contract.getAll", query = "SELECT c FROM Contract c")
public class Contract {
    @Id
    @Column(name = "contract_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "number")
    private long number;

    @ManyToOne
    @JoinColumn(name = "tariff_id")
    private Tariff tariff;

    @Column(name = "blckd_by_cl")
    private boolean isBlockedByClient = false;

    @Column(name = "blckd_by_op")
    private boolean isBlockedByOperator = false;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToMany
    @JoinTable
            (
                    name="connected_option",
                    joinColumns={ @JoinColumn(name="contract_id", referencedColumnName="contract_id") },
                    inverseJoinColumns={ @JoinColumn(name="option_id", referencedColumnName="option_id") }
            )
    private List<Option> options;

    public Contract() {
    }

    public Contract(Client client, long number, Tariff tariff, boolean isBlockedByClient, boolean isBlockedByOperator) {
        this.client = client;
        this.number = number;
        this.tariff = tariff;
        this.isBlockedByClient = isBlockedByClient;
        this.isBlockedByOperator = isBlockedByOperator;
    }

    public long getId() {
        return id;
    }

    public long getNumber() {
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

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", number=" + number +
                ", tariff='" + tariff + '\'' +
                ", isBlockedByClient=" + isBlockedByClient +
                ", isBlockedByOperator=" + isBlockedByOperator +
                ", client=" + client +
                '}';
    }
}
