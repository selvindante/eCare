package ru.tsystems.tsproject.ecare.business;

import ru.tsystems.tsproject.ecare.ECareException;
import ru.tsystems.tsproject.ecare.entities.Contract;
import ru.tsystems.tsproject.ecare.storage.AbstractContractDAO;
import ru.tsystems.tsproject.ecare.storage.SqlContractDAO;
import ru.tsystems.tsproject.ecare.storage.SqlEntityManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * Created by Selvin
 * on 10.10.2014.
 */
public class ContractBusiness {
    private EntityManager em = SqlEntityManager.getEm();
    private AbstractContractDAO cnDAO = new SqlContractDAO(em);

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

    protected Contract loadContract(long id) {
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

    protected void updateContract(Contract cn) {
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

    protected void deleteContract(long id) {
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

    protected List<Contract> getAllContracts() {
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

    protected List<Contract> getAllContractsForClient(long id) {
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

    protected void deleteAllContracts() {
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

    protected long getNumberOfContracts() {
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
}
