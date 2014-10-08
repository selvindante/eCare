package ru.tsystems.tsproject.ecare.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Selvin
 * on 30.09.2014.
 */

@Entity
@Table(name = "client")
@NamedQuery(name = "Client.getAll", query = "SELECT c FROM Client c")
public class Client implements Comparable<Client>{
    @Id
    @Column(name = "client_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /*@OneToOne(fetch=FetchType.LAZY)
    @JoinTable
            (
                    name="authorisation",
                    joinColumns={ @JoinColumn(name="client_id", referencedColumnName="user_id") }
            )*/
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthDate;

    @Column(name = "passport")
    private long passport;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    private List<Contract> contracts = new ArrayList<>();

    public Client() {
    }

    public Client(String name, String lastname, Date birthDate, long passport, String address, String email, String password) {
        this.name = name;
        this.lastname = lastname;
        this.birthDate = birthDate;
        this.passport = passport;
        this.address = address;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public long getPassport() {
        return passport;
    }

    public void setPassport(long passport) {
        this.passport = passport;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void addContract(Contract cn) {
        this.contracts.add(cn);
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", birthDate=" + birthDate +
                ", passport=" + passport +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (passport != client.passport) return false;
        if (address != null ? !address.equals(client.address) : client.address != null) return false;
        if (birthDate != null ? !birthDate.equals(client.birthDate) : client.birthDate != null) return false;
        if (!email.equals(client.email)) return false;
        if (lastname != null ? !lastname.equals(client.lastname) : client.lastname != null) return false;
        if (name != null ? !name.equals(client.name) : client.name != null) return false;
        if (!password.equals(client.password)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + (int) (passport ^ (passport >>> 32));
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + email.hashCode();
        return result;
    }

    @Override
    public int compareTo(Client o) {
        int cmp = name.compareTo(o.name);
        return cmp == 0 ? email.compareTo(o.email) : cmp;
    }
}