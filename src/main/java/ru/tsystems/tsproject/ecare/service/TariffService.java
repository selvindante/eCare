package ru.tsystems.tsproject.ecare.service;

import ru.tsystems.tsproject.ecare.ECareException;
import ru.tsystems.tsproject.ecare.entities.Tariff;
import ru.tsystems.tsproject.ecare.dao.AbstractTariffDAO;
import ru.tsystems.tsproject.ecare.dao.SqlEntityManager;
import ru.tsystems.tsproject.ecare.dao.SqlTariffDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * Created by Selvin
 * on 10.10.2014.
 */
public class TariffService implements ITariffService {
    private EntityManager em = SqlEntityManager.getEm();
    private AbstractTariffDAO trDAO = new SqlTariffDAO(em);

    @Override
    public void createTariff(Tariff tr) {
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

    @Override
    public Tariff loadTariff(long id) throws ECareException {
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

    public void updateTariff(Tariff tr) {
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

    @Override
    public void deleteTariff(long id) throws ECareException {
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

    @Override
    public List<Tariff> getAllTariffs() {
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

    @Override
    public void deleteAllTariffs() {
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

    @Override
    public long getNumberOfTariffs() {
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
