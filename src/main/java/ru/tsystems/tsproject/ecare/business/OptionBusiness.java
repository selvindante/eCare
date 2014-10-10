package ru.tsystems.tsproject.ecare.business;

import ru.tsystems.tsproject.ecare.ECareException;
import ru.tsystems.tsproject.ecare.entities.Option;
import ru.tsystems.tsproject.ecare.storage.AbstractOptionDAO;
import ru.tsystems.tsproject.ecare.storage.SqlEntityManager;
import ru.tsystems.tsproject.ecare.storage.SqlOptionDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * Created by Selvin
 * on 10.10.2014.
 */
public class OptionBusiness {
    private EntityManager em = SqlEntityManager.getEm();
    private AbstractOptionDAO opDAO = new SqlOptionDAO(em);

    protected void createOption(Option op) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            opDAO.createOption(op);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

    protected Option loadOption(long id) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Option op = opDAO.loadOption(id);
            tx.commit();
            if(op == null)  throw new ECareException("Option with id = " + id + " not found.");
            return op;
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

    protected void updateOption(Option op) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            opDAO.updateOption(op);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

    protected void deleteOption(long id) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Option op = opDAO.loadOption(id);
            if(op == null) throw new ECareException("Option with id = " + id + " not exist.");
            opDAO.deleteOption(op);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

    protected List<Option> getAllOptions() {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            List<Option> options = opDAO.getAllOptions();
            tx.commit();
            return options;
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

    protected List<Option> getAllOptionsForTariff(long id) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            List<Option> options = opDAO.getAllOptionsForTariff(id);
            tx.commit();
            return options;
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

    protected void deleteAllOptions() {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            opDAO.deleteAllOptions();
            tx.commit();
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

    protected long getNumberOfOptions() {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            long number = opDAO.size();
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
