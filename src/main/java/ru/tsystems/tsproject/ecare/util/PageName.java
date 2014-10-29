package ru.tsystems.tsproject.ecare.util;

/**
 * Created by Selvin
 * on 29.10.2014.
 */
public enum PageName {
    LOGIN ("Login"),
    REGISTRATION ("Registration"),
    DASHBOARD ("Dashboard"),
    TARIFFS ("Tariffs"),
    TARIFF ("Tariff"),
    NEWTARIFF ("New tariff"),
    OPTION ("Option"),
    OPTIONSETTINGS ("Option settings"),
    NEWOPTION ("New option"),
    CLIENT("Client"),
    EDITCLIENT ("Edit client"),
    CONTRACT ("Contract"),
    NEWCONTRACT ("New contract"),
    CHOOSETARIFF ("Choose tariff"),
    CHOOSEOPTIONS ("Choose options");

    private String title;

    PageName(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
