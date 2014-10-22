package ru.tsystems.tsproject.ecare.service;

import ru.tsystems.tsproject.ecare.ECareException;
import ru.tsystems.tsproject.ecare.dao.AbstractDAO;
import ru.tsystems.tsproject.ecare.dao.SqlContractDAO;
import ru.tsystems.tsproject.ecare.entities.Client;
import ru.tsystems.tsproject.ecare.entities.Contract;
import ru.tsystems.tsproject.ecare.entities.Option;
import ru.tsystems.tsproject.ecare.entities.Tariff;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Selvin
 * on 10.10.2014.
 */
public class ContractService implements IContractService {
    private static ContractService instance;
    private EntityManager em = SqlEntityManager.getEm();
    private AbstractDAO<Contract> DAO = SqlContractDAO.getInstance();
    private SqlContractDAO cnDAO = SqlContractDAO.getInstance();
    private IClientService clientService = ClientService.getInstance();
    //private Logger logger = ECareLogger.getInstance().getLogger();

    private ContractService() {
    }

    public static ContractService getInstance()
    {
        if (instance == null)
        {
            instance = new ContractService();
        }
        return instance;
    }

    @Override
    public Contract saveOrUpdateContract(Contract cn) throws ECareException {
        //logger.info("Save/update contract " + cn + " in DB.");
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Contract contract = DAO.saveOrUpdate(cn);
            tx.commit();
            if(contract == null) {
                ECareException ecx = new ECareException("Failed to save/update contract " + cn + " in DB.");
                //logger.info(ecx.getMessage());
                throw ecx;
            }
            //logger.info("Contract " + contract + " saved/updated in DB.");
            return contract;
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

    @Override
    public Contract loadContract(long id) throws ECareException {
        //logger.info("Load contract with id: " + id + " from DB.");
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Contract cn = DAO.load(id);
            tx.commit();
            if(cn == null) {
                ECareException ecx = new ECareException("Contract with id = " + id + " not found in DB.");
                //logger.info(ecx.getMessage());
                throw ecx;
            }
            //logger.info("Contract " + cn + " loaded from DB.");
            return cn;
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

    @Override
    public Contract findContractByNumber(long number) throws ECareException {
        //logger.info("Find contract by telephone number: " + number + " in DB.");
        Contract cn = null;
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            try {
                cn = cnDAO.findContractByNumber(number);
            } catch(NoResultException nrx) {
                ECareException ecx = new ECareException("Contract with number: " + number + " not found.", nrx);
                //logger.info(ecx.getMessage() + "\n" + nrx.getMessage());
                throw ecx;
            }
            et.commit();
            //logger.info("Contract " + cn + " found and loaded from DB.");
            return cn;
        }
        catch (RuntimeException re) {
            if(et.isActive()) {
                et.rollback();
            }
            throw re;
        }
    }

    @Override
    public void deleteContract(long id) throws ECareException {
        //logger.info("Delete contract with id: " + id + " from DB.");
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Contract cn = DAO.load(id);
            if(cn == null) {
                ECareException ecx = new ECareException("Contract with id = " + id + " not exist.");
                //logger.info(ecx.getMessage());
                throw ecx;
            }
            DAO.delete(cn);
            tx.commit();
            //logger.info("Contract " + cn + " deleted from DB.");
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

    @Override
    public List<Contract> getAllContracts() throws ECareException {
        //logger.info("Get all contracts from DB.");
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            List<Contract> contracts = DAO.getAll();
            tx.commit();
            if(contracts == null) {
                ECareException ecx = new ECareException("Failed to get all contracts from DB.");
                //logger.info(ecx.getMessage());
                throw ecx;
            }
            //logger.info("All contracts obtained from DB.");
            return contracts;
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

    @Override
    public List<Contract> getAllContractsForClient(long id) {
        //logger.info("Get all contracts from DB for client with id: " + id + ".");
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            List<Contract> contracts = cnDAO.getAllContractsForClient(id);
            tx.commit();
            //logger.info("All contracts for client id: " + id + " obtained from DB.");
            return contracts;
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

    @Override
    public void deleteAllContracts() {
        //logger.info("Delete all contracts from DB.");
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            DAO.deleteAll();
            tx.commit();
            //logger.info("All contracts deleted from DB.");
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

    @Override
    public void deleteAllContractsForClient(long id) {
        //logger.info("Delete all contracts from DB for client with id: " + id + ".");
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            cnDAO.deleteAllContractsForClient(id);
            tx.commit();
            //logger.info("All contracts for client id: " + id + " deleted from DB.");
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

    @Override
    public long getNumberOfContracts() {
        //logger.info("Get number of contracts in DB.");
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            long number = DAO.size();
            tx.commit();
            //logger.info(number + "of contracts obtained from DB.");
            return number;
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

    @Override
    public boolean isBlockedByClient(Contract cn) {
        //logger.info("Get info about blocking of contract by client.");
        return cn.isBlockedByClient();
    }

    @Override
    public boolean isBlockedByOperator(Contract cn) {
        //logger.info("Get info about blocking of contract by operator.");
        return cn.isBlockedByClient();
    }

    @Override
    public void blockByClient(Contract cn) throws ECareException {
        //logger.info("Block of contract by client.");
        if(!cn.isBlockedByOperator()) {
            if(!cn.isBlockedByClient()) {
                cn.setBlockByClient(true);
                //logger.info("Contract " + cn.getId() + " is blocked by client.");
            }
            else {
                ECareException ecx = new ECareException("Contract " + cn.getId() + " is already blocked by client.");
                //logger.info(ecx.getMessage());
                throw ecx;
            }
        }
        else {
            ECareException ecx = new ECareException("Contract " + cn.getId() + " is blocked by operator.");
            //logger.info(ecx.getMessage());
            throw ecx;
        }
    }

    @Override
    public void unblockByClient(Contract cn) throws ECareException {
        //logger.info("Unblock of contract by client.");
        if(!cn.isBlockedByOperator()) {
            if(cn.isBlockedByClient()) {
                cn.setBlockByClient(false);
                //logger.info("Contract " + cn.getId() + " is unblocked by client.");
            }
            else {
                ECareException ecx = new ECareException("Contract " + cn.getId() + " is already unblocked by client.");
                //logger.info(ecx.getMessage());
                throw ecx;
            }
        }
        else {
            ECareException ecx = new ECareException("Contract " + cn.getId() + " is blocked by operator.");
            //logger.info(ecx.getMessage());
            throw ecx;
        }
    }

    @Override
    public void blockByOperator(Contract cn) throws ECareException {
        //logger.info("Block of contract by operator.");
        if(!cn.isBlockedByOperator()) {
            cn.setBlockByOperator(true);
            //logger.info("Contract " + cn.getId() + " is blocked by operator.");
        }
        else {
            ECareException ecx = new ECareException("Contract " + cn.getId() + " is already blocked by operator.");
            //logger.info(ecx.getMessage());
            throw ecx;
        }
    }

    @Override
    public void unblockByOperator(Contract cn) throws ECareException {
        //logger.info("Unblock of contract by operator.");
        if(cn.isBlockedByOperator()) {
            cn.setBlockByOperator(false);
            //logger.info("Contract " + cn.getId() + " is unblocked by operator.");
        }
        else {
            ECareException ecx = new ECareException("Contract " + cn.getId() + " is already unblocked by operator.");
            //logger.info(ecx.getMessage());
            throw ecx;
        }
    }

    @Override
    public Contract enableOption(Contract cn, Option op) throws ECareException {
        //logger.info("Enable option id: " + op.getId() + " in contract id: " + cn.getId() + ".");
        List<Option> currentOptions = new ArrayList<>(cn.getOptions());
        Client client = cn.getClient();
        //Check for incompatibility
        if(cn.getOptions().size() != 0) {
            for(Option o: cn.getOptions()) {
                if(o.getIncompatibleOptions().contains(op)) {
                    ECareException ecx = new ECareException("Option id: " + op.getId() + " is incompatible with option id: " + o.getId() + " in contract " + cn.getId() + ".");
                    //logger.info(ecx.getMessage());
                    throw ecx;
                }
            }
        }
        if(!cn.getOptions().contains(op)) {
            cn.addOption(op);
            if(!currentOptions.contains(op)) {
                client.setAmount(client.getAmount() - op.getCostOfConnection());
            }
            //logger.info("Option id: " + op.getId() + " enabled in contract id: " + cn.getId() + ".");
            for(Option dependentOption: op.getDependentOptions()) {
                if(!cn.getOptions().contains(dependentOption)) {
                    cn.addOption(dependentOption);
                    if(!currentOptions.contains(dependentOption)) {
                        client.setAmount(client.getAmount() - dependentOption.getCostOfConnection());
                    }
                    //logger.info("Dependent option id: " + dependentOption.getId() + " enabled in contract id: " + cn.getId() + ".");
                }
            }
        }
        else {
            ECareException ecx = new ECareException("Option id: " + op.getId() + " is already enabled in contract " + cn.getId() + ".");
            //logger.info(ecx.getMessage());
            throw ecx;
        }
        clientService.saveOrUpdateClient(client);
        return cn;
    }

    @Override
    public Contract disableOption(Contract cn, Option op) throws ECareException {
        //logger.info("Disable option id: " + op.getId() + " in contract id: " + cn.getId() + ".");
        if(cn.getOptions().contains(op)) {
            cn.deleteOption(op);
            //logger.info("Option id: " + op.getId() + " disabled in in contract id: " + cn.getId() + ".");
            for(Option dependentOption: op.getDependentOptions()) {
                if(cn.getOptions().contains(dependentOption)) {
                    cn.deleteOption(dependentOption);
                    //logger.info("Dependent option id: " + dependentOption.getId() + " disabled in contract id: " + cn.getId() + ".");
                }
            }
        }
        else {
            ECareException ecx = new ECareException("Option " + op.getId() + " is not enabled yet in contract " + cn.getId() + ".");
            //logger.info(ecx.getMessage());
            throw ecx;
        }
        return cn;
    }

    @Override
    public void setTariff(Contract contract, Tariff tariff) {
        Tariff currentTariff = contract.getTariff();
        Client client = contract.getClient();
        contract.setTariff(tariff);
        if(!tariff.equals(currentTariff)) {
            client.setAmount(client.getAmount() - tariff.getPrice());
        }
        clientService.saveOrUpdateClient(client);
    }
}
