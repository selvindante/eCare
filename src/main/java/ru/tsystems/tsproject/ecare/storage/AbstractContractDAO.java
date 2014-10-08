package ru.tsystems.tsproject.ecare.storage;

import ru.tsystems.tsproject.ecare.entities.Contract;

import java.util.List;

/**
 * Created by Selvin
 * on 06.10.2014.
 */
public abstract class AbstractContractDAO {
    protected abstract void doCreateContract(Contract cn);
    protected abstract Contract doLoadContract(long id);
    protected abstract void doUpdateContract(Contract cn);
    protected abstract void doDeleteContract(long id);
    protected abstract List<Contract> doGetAll();
    protected abstract void doDeleteAll();
    protected abstract long doSize();
    protected abstract List<Contract> doGetAllForClient(long id);

    public void createContract(Contract cn){
        doCreateContract(cn);
    }

    public Contract loadContract(long id) {
        return doLoadContract(id);
    }

    public void updateContract(Contract cn) {
        doUpdateContract(cn);
    }

    public void deleteContract(long id) {
        doDeleteContract(id);
    }

    public List<Contract> getAll() {
        return doGetAll();
    }

    public List<Contract> getAllForClient(long id) {
        return doGetAllForClient(id);
    }


    public void deleteAll() {
        doDeleteAll();
    }

    public long size() {
        return doSize();
    }
}
