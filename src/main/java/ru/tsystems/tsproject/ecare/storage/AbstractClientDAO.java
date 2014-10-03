package ru.tsystems.tsproject.ecare.storage;

import ru.tsystems.tsproject.ecare.model.Client;

/**
 * Created by Selvin
 * on 02.10.2014.
 */

public abstract class AbstractClientDAO {
    protected abstract void doCreateClient(Client cl);
    protected abstract Client doLoadClient(String email, String password);
    protected abstract void doUpdateClient(Client cl);
    protected abstract void doDeleteClient(String email, String password);

    public void createClient(Client cl){
        doCreateClient(cl);
    }

    public Client loadClient(String email, String password) {
        return doLoadClient(email, password);
    }

    public void updateClient(Client cl) {
        doUpdateClient(cl);
    }

    public void deleteClient(String email, String password) {
        doDeleteClient(email, password);
    }
}
