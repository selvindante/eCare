package ru.tsystems.tsproject.ecare.service;

import ru.tsystems.tsproject.ecare.ECareException;
import ru.tsystems.tsproject.ecare.dao.AbstractContractDAO;
import ru.tsystems.tsproject.ecare.dao.SqlContractDAO;
import ru.tsystems.tsproject.ecare.dao.SqlEntityManager;
import ru.tsystems.tsproject.ecare.entities.Contract;
import ru.tsystems.tsproject.ecare.entities.Option;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import java.util.List;

/**
 * Created by Selvin
 * on 10.10.2014.
 */
public class ContractService implements IContractService {
    private EntityManager em = SqlEntityManager.getEm();
    private AbstractContractDAO cnDAO = new SqlContractDAO(em);

    @Override
    public void createContract(Contract cn) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            cnDAO.createContract(cn);
            tx.commit();
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
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Contract cr = cnDAO.loadContract(id);
            tx.commit();
            if(cr == null)  throw new ECareException("Contract with id = " + id + " not found.");
            return cr;
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

    @Override
    public Contract findContractByNumber(long number) {
        Contract cn = null;
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            try {
                cn = cnDAO.findContractByNumber(number);
            } catch(NoResultException nrex) {
                throw new ECareException("Incorrect number or contract does not exist.", nrex);
            }
            et.commit();
            return cn;
        }
        catch (RuntimeException re) {
            if(et.isActive()) {
                et.rollback();
            }
            throw re;
        }
    }

    public void updateContract(Contract cn) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            cnDAO.updateContract(cn);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

    @Override
    public void deleteContract(long id) throws ECareException {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Contract cn = cnDAO.loadContract(id);
            if(cn == null) throw new ECareException("Contract with id = " + id + " not exist.");
            cnDAO.deleteContract(cn);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

    @Override
    public List<Contract> getAllContracts() {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            List<Contract> contracts = cnDAO.getAllContracts();
            tx.commit();
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
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            List<Contract> contracts = cnDAO.getAllContractsForClient(id);
            tx.commit();
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
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            cnDAO.deleteAllContracts();
            tx.commit();
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
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            long number = cnDAO.size();
            tx.commit();
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
        return cn.isBlockedByClient();
    }

    @Override
    public boolean isBlockedByOperator(Contract cn) {
        return cn.isBlockedByClient();
    }

    @Override
    public void blockByClient(Contract cn) throws ECareException {
        if(!cn.isBlockedByOperator()) {
            if(!cn.isBlockedByClient()) {
                cn.setBlockByClient(true);
            }
            else throw new ECareException("Contract " + cn.getId() + " is already blocked by client.");
        }
        else throw new ECareException("Contract " + cn.getId() + " is blocked by operator.");
    }

    @Override
    public void unblockByClient(Contract cn) throws ECareException {
        if(!cn.isBlockedByOperator()) {
            if(cn.isBlockedByClient()) {
                cn.setBlockByClient(false);
            }
            else throw new ECareException("Contract " + cn.getId() + " is already unblocked by client.");
        }
        else throw new ECareException("Contract " + cn.getId() + " is blocked by operator.");
    }

    @Override
    public void blockByOperator(Contract cn) throws ECareException {
        if(!cn.isBlockedByOperator()) {
            cn.setBlockByOperator(true);
        }
        else throw new ECareException("Contract " + cn.getId() + " is already blocked by operator.");
    }

    @Override
    public void unblockByOperator(Contract cn) throws ECareException {
        if(cn.isBlockedByOperator()) {
            cn.setBlockByOperator(false);
        }
        else throw new ECareException("Contract " + cn.getId() + " is already unblocked by operator.");
    }

    @Override
    public void enableOption(Contract cn, Option op) throws ECareException {
        if(!cn.getOptions().contains(op)) {
            cn.addOption(op);
            for(Option dependentOption: op.getDependentOptions()) {
                if(!cn.getOptions().contains(dependentOption)) cn.addOption(dependentOption);
            }
        }
        else throw new ECareException("Option " + op.getId() + ":" + op.getTitle() + " is already enabled for contract " + cn.getId() + ".");
    }

    @Override
    public void disableOption(Contract cn, Option op) throws ECareException {
        if(cn.getOptions().contains(op)) {
            cn.deleteOption(op);
            for(Option dependentOption: op.getDependentOptions()) {
                if(cn.getOptions().contains(dependentOption)) cn.deleteOption(dependentOption);
            }
        }
        else throw new ECareException("Option " + op.getId() + ":" + op.getTitle() + " is not enabled yet for contract " + cn.getId() + ".");
    }
}
