package ru.tsystems.tsproject.ecare.storage;

import ru.tsystems.tsproject.ecare.model.Client;

/**
 * Created by Selvin
 * on 02.10.2014.
 */
public class SqlClientDAO extends AbstractClientDAO {

    @Override
    protected void doCreateClient(Client cl) {

    }

    @Override
    protected Client doLoadClient(String email, String password) {
        return null;
    }

    @Override
    protected void doUpdateClient(Client cl) {

    }

    @Override
    protected void doDeleteClient(String email, String password) {

    }
}
