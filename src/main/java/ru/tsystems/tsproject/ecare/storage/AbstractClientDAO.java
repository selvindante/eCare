package ru.tsystems.tsproject.ecare.storage;

import ru.tsystems.tsproject.ecare.entities.Client;

import java.util.List;

/**
 * Created by Selvin
 * on 02.10.2014.
 */

public abstract class AbstractClientDAO {
    protected abstract void doCreateClient(Client cl);
    protected abstract Client doLoadClient(long id);
    protected abstract void doUpdateClient(Client cl);
    protected abstract void doDeleteClient(long id);
    protected abstract List<Client> doGetAll();
    protected abstract void doDeleteAll();
    protected abstract long doSize();

    public void createClient(Client cl){
        doCreateClient(cl);
    }

    public Client loadClient(long id) {
        return doLoadClient(id);
    }

    public void updateClient(Client cl) {
        doUpdateClient(cl);
    }

    public void deleteClient(long id) {
        doDeleteClient(id);
    }

    public List<Client> getAll() {
        return doGetAll();
    }

    public void deleteAll() {
        doDeleteAll();
    }

    public long size() {
        return doSize();
    }
}
