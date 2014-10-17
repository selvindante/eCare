package ru.tsystems.tsproject.ecare.service;

import ru.tsystems.tsproject.ecare.ECareException;
import ru.tsystems.tsproject.ecare.ECareLogger;
import ru.tsystems.tsproject.ecare.dao.AbstractDAO;
import ru.tsystems.tsproject.ecare.dao.SqlClientDAO;
import ru.tsystems.tsproject.ecare.entities.Client;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Selvin
 * on 10.10.2014.
 */

public class ClientService implements IClientService {
    private static ClientService instance;
    private EntityManager em = SqlEntityManager.getEm();
    private AbstractDAO<Client> DAO = SqlClientDAO.getInstance();
    private SqlClientDAO clDAO = SqlClientDAO.getInstance();
    private Logger logger = ECareLogger.getInstance().getLogger();

    private ClientService() {
    }

    public static ClientService getInstance()
    {
        if (instance == null)
        {
            instance = new ClientService();
        }
        return instance;
    }

    @Override
    public Client saveOrUpdateClient(Client cl) throws ECareException {
        logger.info("Save/update client " + cl + " in DB.");
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Client client = DAO.saveOrUpdate(cl);
            et.commit();
            if(client == null) {
                ECareException ecx = new ECareException("Failed to save/update client " + cl + " in DB.");
                logger.info(ecx.getMessage());
                throw ecx;
            }
            logger.info("Client " + client + " saved/updated in DB.");
            return client;
        }
        catch (RuntimeException re) {
            if(et.isActive()) {
                et.rollback();
            }
            throw re;
        }
    }

    @Override
    public Client loadClient(long id) throws ECareException {
        logger.info("Load client with id: " + id + " from DB.");
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Client cl = DAO.load(id);
            et.commit();
            if(cl == null) {
                ECareException ecx = new ECareException("Client with id = " + id + " not found in DB.");
                logger.info(ecx.getMessage());
                throw ecx;
            }
            logger.info("Client " + cl + " loaded from DB.");
            return cl;
        }
        catch (RuntimeException re) {
            if(et.isActive()) {
                et.rollback();
            }
            throw re;
        }
    }

    @Override
    public Client findClient(String login, String password) throws ECareException {
        logger.info("Find client with login: " + login + " and password:" + password + " in DB.");
        Client cl = null;
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            try {
                cl = clDAO.findClientByLoginAndPassword(login, password);
            } catch(NoResultException nrx) {
                ECareException ecx = new ECareException("Incorrect login/password or client does not exist.", nrx);
                logger.info(ecx.getMessage() + "\n" + nrx.getMessage());
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

    @Override
    public Client findClientByNumber(long number) throws ECareException {
        logger.info("Find client with telephone number: " + number + " in DB.");
        EntityTransaction et = em.getTransaction();
        Client cl = null;
        try {
            et.begin();
            try {
                cl = clDAO.findClientByNumber(number);
            } catch(NoResultException nrx) {
                ECareException ecx = new ECareException("Client with number: " + number + " not found.", nrx);
                logger.info(ecx.getMessage() + "\n" + nrx.getMessage());
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

    @Override
    public void deleteClient(long id) throws ECareException {
        logger.info("Delete client with id: " + id + " from DB.");
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Client cl = DAO.load(id);
            if(cl == null) {
                ECareException ecx = new ECareException("Client with id = " + id + " not exist.");
                logger.info(ecx.getMessage());
                throw ecx;
            }
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

    @Override
    public List<Client> getAllClients() throws ECareException {
        logger.info("Get all clients from DB.");
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            List<Client> clients = DAO.getAll();
            tx.commit();
            if(clients == null) {
                ECareException ecx = new ECareException("Failed to get all clients from DB.");
                logger.info(ecx.getMessage());
                throw ecx;
            }
            logger.info("All clients obtained from DB.");
            return clients;
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

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
