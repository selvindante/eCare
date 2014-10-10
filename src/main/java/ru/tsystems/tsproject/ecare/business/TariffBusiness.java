package ru.tsystems.tsproject.ecare.business;

import ru.tsystems.tsproject.ecare.ECareException;
import ru.tsystems.tsproject.ecare.entities.Tariff;
import ru.tsystems.tsproject.ecare.storage.AbstractTariffDAO;
import ru.tsystems.tsproject.ecare.storage.SqlEntityManager;
import ru.tsystems.tsproject.ecare.storage.SqlTariffDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * Created by Selvin
 * on 10.10.2014.
 */
public class TariffBusiness {
    private EntityManager em = SqlEntityManager.getEm();
    private AbstractTariffDAO trDAO = new SqlTariffDAO(em);

    protected void createTariff(Tariff tr) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            trDAO.createTariff(tr);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

    protected Tariff loadTariff(long id) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Tariff tr = trDAO.loadTariff(id);
            et.commit();
            if(tr == null)  throw new ECareException("Tariff with id = " + id + " not found.");
            return tr;
        }
        catch (RuntimeException re) {
            if(et.isActive()) {
                et.rollback();
            }
            throw re;
        }
    }

    protected void updateTariff(Tariff tr) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            trDAO.updateTariff(tr);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

    protected void deleteTariff(long id) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Tariff tr = trDAO.loadTariff(id);
            if(tr == null) throw new ECareException("Tariff with id = " + id + " not exist.");
            trDAO.deleteTariff(tr);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

    protected List<Tariff> getAllTariffs() {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            List<Tariff> tariffs = trDAO.getAllTariffs();
            tx.commit();
            return tariffs;
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

    protected void deleteAllTariffs() {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            trDAO.deleteAllTariffs();
            tx.commit();
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

    protected long getNumberOfTariffs() {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            long number = trDAO.size();
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
