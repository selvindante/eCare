package ru.tsystems.tsproject.ecare.service;

import org.apache.log4j.Logger;
import ru.tsystems.tsproject.ecare.ECareException;
import ru.tsystems.tsproject.ecare.dao.AbstractDAO;
import ru.tsystems.tsproject.ecare.dao.SqlClientDAO;
import ru.tsystems.tsproject.ecare.entities.Client;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import java.util.List;

/**
 * This class is the implementation of IClientService for working with client DAO.
 * Class ClientService is a singleton.
 *
 * @author Starostin Konstantin
 * @see ru.tsystems.tsproject.ecare.service.IClientService
 */
public class ClientService implements IClientService {

    /*Instance of the singleton class*/
    private static ClientService instance;

    /*Entity manager for working with JPA methods*/
    private EntityManager em = SqlEntityManager.getEm();

    /*SQL client implementations of abstract DAO class*/
    private AbstractDAO<Client> DAO = SqlClientDAO.getInstance();
    private SqlClientDAO clDAO = SqlClientDAO.getInstance();

    /*Logger for client service operations*/
    private static Logger logger = Logger.getLogger("ClientService");

    /*Private constructor of singleton class*/
    private ClientService() {
    }

    /**
     * This method return instance of singleton class ClientService.
     * @return instance of class.
     */
    public static ClientService getInstance()
    {
        if (instance == null)
        {
            instance = new ClientService();
        }
        return instance;
    }

    /**
     * Method implements saving or updating of clients in the database.
     *
     * @param cl client entity to be saved or updated.
     * @return saved or updated client entity.
     * @throws ECareException if an error occurred during saving or updating of entity
     * and DAO returns null.
     */
    @Override
    public Client saveOrUpdateClient(Client cl) throws ECareException {
        logger.info("Save/update client " + cl + " in DB.");
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Client client = DAO.saveOrUpdate(cl);
            et.commit();
            //If DAO returns null method will throws an ECareException
            if(client == null) {
                ECareException ecx = new ECareException("Failed to save/update client " + cl + " in DB.");
                logger.error(ecx.getMessage(), ecx);
                throw ecx;
            }
            logger.info("Client " + client + " saved/updated in DB.");
            //else client will be saved and method returns client entity
            return client;
        }
        catch (RuntimeException re) {
            if(et.isActive()) {
                et.rollback();
            }
            throw re;
        }
    }

    /**
     * Method implements loading of clients from the database.
     *
     * @param id client id for search that client in the database.
     * @return loaded client entity.
     * @throws ECareException if an error occurred during loading of entity
     * and DAO returns null.
     */
    @Override
    public Client loadClient(long id) throws ECareException {
        logger.info("Load client with id: " + id + " from DB.");
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Client cl = DAO.load(id);
            et.commit();
            //If DAO returns null method will throws an ECareException
            if(cl == null) {
                ECareException ecx = new ECareException("Client with id = " + id + " not found in DB.");
                logger.warn(ecx.getMessage(), ecx);
                throw ecx;
            }
            logger.info("Client " + cl + " loaded from DB.");
            //else method returns client entity
            return cl;
        }
        catch (RuntimeException re) {
            if(et.isActive()) {
                et.rollback();
            }
            throw re;
        }
    }

    /**
     * Method implements finding of clients by their login and password in
     * the database.
     *
     * @param login client login for search that client in the database.
     * @param password client password for search that client in the database.
     * @return found client entity.
     * @throws ECareException if DAO returns NoResultException during finding of client
     * in the database.
     */
    @Override
    public Client findClient(String login, String password) throws ECareException {
        logger.info("Find client with login: " + login + " and password:" + password + " in DB.");
        Client cl = null;
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            try {
                // Search of client in the database by DAO method.
                cl = clDAO.findClientByLoginAndPassword(login, password);
                // If client does not exist in database, block try catches the NoResultException and
                // throws an ECareException.
            } catch(NoResultException nrx) {
                ECareException ecx = new ECareException("Incorrect login/password or client does not exist.", nrx);
                logger.warn(ecx.getMessage(), nrx);
                throw ecx;
            }
            et.commit();
            logger.info("Client " + cl + " found and loaded from DB.");
            return cl;
        }
        catch (RuntimeException re) {
            if(et.isActive()) {
                et.rollback();
            }
            throw re;
        }
    }

    /**
     * Method implements finding of clients by their telephone number in the database.
     *
     * @param number telephone number of client for search that client in the database.
     * @return found client entity.
     * @throws ECareException if DAO returns NoResultException during finding of client
     * in the database.
     */
    @Override
    public Client findClientByNumber(long number) throws ECareException {
        logger.info("Find client with telephone number: " + number + " in DB.");
        EntityTransaction et = em.getTransaction();
        Client cl = null;
        try {
            et.begin();
            try {
                // Search of client in the database by DAO method.
                cl = clDAO.findClientByNumber(number);
                // If client does not exist in database, block try catches the NoResultException and
                // throws an ECareException.
            } catch(NoResultException nrx) {
                ECareException ecx = new ECareException("Client with number: " + number + " not found.", nrx);
                logger.warn(ecx.getMessage(), nrx);
                throw ecx;
            }
            et.commit();
            logger.info("Client " + cl + " found and loaded from DB.");
            return cl;
        }
        catch (RuntimeException re) {
            if(et.isActive()) {
                et.rollback();
            }
            throw re;
        }
    }

    /**
     * Method implements deleting of clients from the database.
     *
     * @param id client id for deleting that client from the database.
     * @throws ECareException if an error occurred during intermediate loading
     * of entity and DAO returns null.
     */
    @Override
    public void deleteClient(long id) throws ECareException {
        logger.info("Delete client with id: " + id + " from DB.");
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Client cl = DAO.load(id);
            //If DAO returns null method will throws an ECareException.
            if(cl == null) {
                ECareException ecx = new ECareException("Client with id = " + id + " not exist.");
                logger.warn(ecx.getMessage(), ecx);
                throw ecx;
            }
            // Else client will be deleted from the database.
            DAO.delete(cl);
            tx.commit();
            logger.info("Client " + cl + " deleted from DB.");
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

    /**
     * Method implements receiving of all clients from the database.
     *
     * @return list of received clients.
     * @throws ECareException if an error occurred during receiving of entities
     * and DAO returns null.
     */
    @Override
    public List<Client> getAllClients() throws ECareException {
        logger.info("Get all clients from DB.");
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            List<Client> clients = DAO.getAll();
            tx.commit();
            //If DAO returns null method will throws an ECareException.
            if(clients == null) {
                ECareException ecx = new ECareException("Failed to get all clients from DB.");
                logger.error(ecx.getMessage(), ecx);
                throw ecx;
            }
            logger.info("All clients obtained from DB.");
            // Else method returns list of client entities
            return clients;
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

    /**
     * Method implements deleting of all clients from the database.
     */
    @Override
    public void deleteAllClients() {
        logger.info("Delete all clients from DB.");
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            DAO.deleteAll();
            tx.commit();
            logger.info("All clients deleted from DB.");
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

    /**
     * Method implements receiving number of all clients from the database.
     *
     * @return number of clients in the database.
     */
    @Override
    public long getNumberOfClients() {
        logger.info("Get number of clients in DB.");
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            long number = DAO.size();
            tx.commit();
            logger.info(number + "of clients obtained fromDB.");
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
