package ru.tsystems.tsproject.ecare.service;

import ru.tsystems.tsproject.ecare.entities.Contract;
import ru.tsystems.tsproject.ecare.entities.Option;

import java.util.List;

/**
 * Created by Selvin
 * on 15.10.2014.
 */
public interface IContractService {

    public Contract saveOrUpdateContract(Contract cn);

    public Contract loadContract(long id);

    public Contract findContractByNumber(long number);

    public void deleteContract(long id);

    public List<Contract> getAllContracts();

    public List<Contract> getAllContractsForClient(long id);

    public void deleteAllContracts();

    public void deleteAllContractsForClient(long id);

    public long getNumberOfContracts();

    public boolean isBlockedByClient(Contract cn);

    public boolean isBlockedByOperator(Contract cn);

    public void blockByClient(Contract cn);

    public void unblockByClient(Contract cn);

    public void blockByOperator(Contract cn);

    public void unblockByOperator(Contract cn);

    public void enableOption(Contract cn, Option op);

    public void disableOption(Contract cn, Option op);
}
