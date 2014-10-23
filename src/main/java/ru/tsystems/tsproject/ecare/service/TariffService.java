package ru.tsystems.tsproject.ecare.service;

import org.apache.log4j.Logger;
import ru.tsystems.tsproject.ecare.ECareException;
import ru.tsystems.tsproject.ecare.dao.AbstractDAO;
import ru.tsystems.tsproject.ecare.dao.SqlTariffDAO;
import ru.tsystems.tsproject.ecare.entities.Tariff;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * This class is the implementation of ITariffService for working with tariff DAO
 * and tariff entities. Class TariffService is a singleton.
 *
 * @author Starostin Konstantin
 * @see ru.tsystems.tsproject.ecare.service.ITariffService
 */
public class TariffService implements ITariffService {

    /*Instance of the singleton class*/
    private static TariffService instance;

    /*Entity manager for working with JPA methods*/
    private EntityManager em = SqlEntityManager.getEm();

    /*SQL tariff implementations of abstract DAO class*/
    private AbstractDAO<Tariff> trDAO = SqlTariffDAO.getInstance();

    /*Logger for tariff service operations*/
    private static Logger logger = Logger.getLogger("TariffService");

    /*Private constructor of singleton class*/
    private TariffService() {
    }

    /**
     * This method return instance of singleton class TariffService.
     *
     * @return instance of class.
     */
    public static TariffService getInstance()
    {
        if (instance == null)
        {
            instance = new TariffService();
        }
        return instance;
    }

    /**
     * This method implements saving or updating of tariff in the database.
     *
     * @param tr tariff entity to be saved or updated.
     * @return saved or updated tariff entity.
     * @throws ECareException if an error occurred during saving or updating of entity
     * and DAO returns null.
     */
    @Override
    public Tariff saveOrUpdateTariff(Tariff tr) throws ECareException {
        logger.info("Save/update tariff " + tr + " in DB.");
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Tariff tariff = trDAO.saveOrUpdate(tr);
            tx.commit();
            //If DAO returns null method will throws an ECareException.
            if(tariff == null) {
                ECareException ecx = new ECareException("Failed to save/update tariff " + tr + " in DB.");
                logger.error(ecx.getMessage(), ecx);
                throw ecx;
            }
            logger.info("Tariff " + tariff + " saved in DB.");
            //Else tariff will be saved and method returns tariff entity.
            return tariff;
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

    /**
     * This method implements loading of tariff from the database.
     *
     * @param id tariff id for search that tariff in the database.
     * @return loaded tariff entity.
     * @throws ECareException if an error occurred during loading of entity
     * and DAO returns null.
     */
    @Override
    public Tariff loadTariff(long id) throws ECareException {
        logger.info("Load tariff with id: " + id + " from DB.");
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Tariff tr = trDAO.load(id);
            et.commit();
            //If DAO returns null method will throws an ECareException.
            if(tr == null) {
                ECareException ecx = new ECareException("Tariff with id = " + id + " not found.");
                logger.warn(ecx.getMessage(), ecx);
                throw ecx;
            }
            logger.info("Tariff " + tr + " loaded from DB.");
            //Else method returns tariff entity.
            return tr;
        }
        catch (RuntimeException re) {
            if(et.isActive()) {
                et.rollback();
            }
            throw re;
        }
    }

    /**
     * This method implements deleting of tariff from the database.
     *
     * @param id tariff id for deleting that tariff from the database.
     * @throws ECareException if an error occurred during intermediate loading
     * of entity and DAO returns null.
     */
    @Override
    public void deleteTariff(long id) throws ECareException {
        logger.info("Delete tariff with id: " + id + " from DB.");
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Tariff tr = trDAO.load(id);
            //If DAO returns null method will throws an ECareException.
            if(tr == null) {
                ECareException ecx = new ECareException("Tariff with id = " + id + " not exist.");
                logger.warn(ecx.getMessage(), ecx);
                throw ecx;
            }
            // Else tariff will be deleted from the database.
            trDAO.delete(tr);
            tx.commit();
            logger.info("Tariff " + tr + " deleted from DB.");
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

    /**
     * This method implements receiving of all options from the database.
     *
     * @return list of received tariffs.
     */
    @Override
    public List<Tariff> getAllTariffs() {
        logger.info("Get all tariffs from DB.");
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            List<Tariff> tariffs = trDAO.getAll();
            tx.commit();
            //If DAO returns null method will throws an ECareException.
            if(tariffs == null) {
                ECareException ecx = new ECareException("Failed to get all tariffs from DB.");
                logger.warn(ecx.getMessage(), ecx);
                throw ecx;
            }
            logger.info("All tariffs obtained from DB.");
            // Else method returns list of tariff entities.
            return tariffs;
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

    /**
     * This method implements deleting of all tariffs from the database.
     */
    @Override
    public void deleteAllTariffs() {
        logger.info("Delete all tariffs from DB.");
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            trDAO.deleteAll();
            tx.commit();
            logger.info("All tariffs deleted from DB.");
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

    /**
     * This method implements receiving number of all tariffs from the storage.
     *
     * @return number of tariffs in the storage.
     */
    @Override
    public long getNumberOfTariffs() {
        logger.info("Get number of tariffs in DB.");
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            long number = trDAO.size();
            tx.commit();
            logger.info(number + " of tariffs obtained fromDB.");
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
