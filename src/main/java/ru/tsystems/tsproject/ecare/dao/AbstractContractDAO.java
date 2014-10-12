package ru.tsystems.tsproject.ecare.dao;

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
    protected abstract void doDeleteContract(Contract cn);
    protected abstract List<Contract> doGetAllContracts();
    protected abstract void doDeleteAllContracts();
    protected abstract long doSize();
    protected abstract List<Contract> doGetAllContractsForClient(long id);

    public void createContract(Contract cn){
        doCreateContract(cn);
    }

    public Contract loadContract(long id) {
        return doLoadContract(id);
    }

    public void updateContract(Contract cn) {
        doUpdateContract(cn);
    }

    public void deleteContract(Contract cn) {
        doDeleteContract(cn);
    }

    public List<Contract> getAllContracts() {
        return doGetAllContracts();
    }

    public List<Contract> getAllContractsForClient(long id) {
        return doGetAllContractsForClient(id);
    }


    public void deleteAllContracts() {
        doDeleteAllContracts();
    }

    public long size() {
        return doSize();
    }
}
