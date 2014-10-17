package ru.tsystems.tsproject.ecare.service;

import ru.tsystems.tsproject.ecare.ECareException;
import ru.tsystems.tsproject.ecare.dao.AbstractDAO;
import ru.tsystems.tsproject.ecare.dao.SqlTariffDAO;
import ru.tsystems.tsproject.ecare.entities.Tariff;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * Created by Selvin
 * on 10.10.2014.
 */
public class TariffService implements ITariffService {
    private static TariffService instance;
    private EntityManager em = SqlEntityManager.getEm();
    private AbstractDAO<Tariff> trDAO = SqlTariffDAO.getInstance();

    private TariffService() {
    }

    public static TariffService getInstance()
    {
        if (instance == null)
        {
            instance = new TariffService();
        }
        return instance;
    }

    @Override
    public Tariff saveOrUpdateTariff(Tariff tr) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            tr = trDAO.saveOrUpdate(tr);
            tx.commit();
            return tr;
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
            Tariff tr = trDAO.load(id);
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

    @Override
    public void deleteTariff(long id) throws ECareException {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Tariff tr = trDAO.load(id);
            if(tr == null) throw new ECareException("Tariff with id = " + id + " not exist.");
            trDAO.delete(tr);
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
            List<Tariff> tariffs = trDAO.getAll();
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
            trDAO.deleteAll();
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
