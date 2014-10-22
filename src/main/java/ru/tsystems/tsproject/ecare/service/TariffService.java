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
    //private Logger logger = ECareLogger.getInstance().getLogger();

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
    public Tariff saveOrUpdateTariff(Tariff tr) throws ECareException {
        //logger.info("Save/update tariff " + tr + " in DB.");
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Tariff tariff = trDAO.saveOrUpdate(tr);
            tx.commit();
            if(tariff == null) {
                ECareException ecx = new ECareException("Failed to save/update tariff " + tr + " in DB.");
                //logger.info(ecx.getMessage());
                throw ecx;
            }
            //logger.info("Tariff " + tariff + " saved in DB.");
            return tariff;
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
        //logger.info("Load tariff with id: " + id + " from DB.");
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Tariff tr = trDAO.load(id);
            et.commit();
            if(tr == null) {
                ECareException ecx = new ECareException("Tariff with id = " + id + " not found.");
                //logger.info(ecx.getMessage());
                throw ecx;
            }
            //logger.info("Tariff " + tr + " loaded from DB.");
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
        //logger.info("Delete tariff with id: " + id + " from DB.");
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Tariff tr = trDAO.load(id);
            if(tr == null) {
                ECareException ecx = new ECareException("Tariff with id = " + id + " not exist.");
                //logger.info(ecx.getMessage());
                throw ecx;
            }
            trDAO.delete(tr);
            tx.commit();
            //logger.info("Tariff " + tr + " deleted from DB.");
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
        //logger.info("Get all tariffs from DB.");
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            List<Tariff> tariffs = trDAO.getAll();
            tx.commit();
            if(tariffs == null) {
                ECareException ecx = new ECareException("Failed to get all tariffs from DB.");
                //logger.info(ecx.getMessage());
                throw ecx;
            }
            //logger.info("All tariffs obtained from DB.");
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
        //logger.info("Delete all tariffs from DB.");
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            trDAO.deleteAll();
            tx.commit();
            //logger.info("All tariffs deleted from DB.");
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
        //logger.info("Get number of tariffs in DB.");
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            long number = trDAO.size();
            tx.commit();
            //logger.info(number + " of tariffs obtained fromDB.");
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
