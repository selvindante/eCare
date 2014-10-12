package ru.tsystems.tsproject.ecare.dao;

import ru.tsystems.tsproject.ecare.entities.Client;

import java.util.List;

/**
 * Created by Selvin
 * on 02.10.2014.
 */

public abstract class AbstractClientDAO {

    protected abstract void doCreateClient(Client cl);
    protected abstract Client doLoadClient(long id);
    protected abstract Client doFindClientByNumber(long number);
    protected abstract void doUpdateClient(Client cl);
    protected abstract void doDeleteClient(Client cl);
    protected abstract List<Client> doGetAllClients();
    protected abstract void doDeleteAllClients();
    protected abstract long doSize();

    public void createClient(Client cl){
        doCreateClient(cl);
    }

    public Client loadClient(long id) {
        return doLoadClient(id);
    }

    public Client findClientByNumber(long number) {
        return doFindClientByNumber(number);
    }

    public void updateClient(Client cl) {
        doUpdateClient(cl);
    }

    public void deleteClient(Client cl) {
        doDeleteClient(cl);
    }

    public List<Client> getAllClients() {
        return doGetAllClients();
    }

    public void deleteAllClients() {
        doDeleteAllClients();
    }

    public long size() {
        return doSize();
    }
}
