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
public class Client {
    private String name;
    private String lastname;
    private String birthDate;
    private long passport;
    private String address;
    private Map<Integer, Contract> contracts = new HashMap<>();
    @Id
    private String email;
    private String password;

    public Client() {
    }

    public Client(String name, String lastname, String birthDate, long passport, String address, Map<Integer, Contract> contracts, String email, String password) {
        this.name = name;
        this.lastname = lastname;
        this.birthDate = birthDate;
        this.passport = passport;
        this.address = address;
        this.contracts = contracts;
        this.email = email;
        this.password = password;
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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public long getPassport() {
        return passport;
    }

    public void setPassport(int passport) {
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
}
