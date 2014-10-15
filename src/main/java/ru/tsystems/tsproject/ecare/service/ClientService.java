package ru.tsystems.tsproject.ecare.service;

import ru.tsystems.tsproject.ecare.ECareException;
import ru.tsystems.tsproject.ecare.dao.AbstractClientDAO;
import ru.tsystems.tsproject.ecare.dao.SqlClientDAO;
import ru.tsystems.tsproject.ecare.dao.SqlEntityManager;
import ru.tsystems.tsproject.ecare.entities.Client;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import java.util.List;

/**
 * Created by Selvin
 * on 10.10.2014.
 */

public class ClientService implements IClientService {
    private EntityManager em = SqlEntityManager.getEm();
    private AbstractClientDAO clDAO = new SqlClientDAO(em);

    @Override
    public void createClient(Client cl) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            clDAO.createClient(cl);
            et.commit();
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
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Client cl = clDAO.loadClient(id);
            et.commit();
            if(cl == null)  throw new ECareException("Client with id = " + id + " not found.");
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
        Client cl = null;
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            try {
                cl = clDAO.findClientByLoginAndPassword(login, password);
            } catch(NoResultException nrx) {
                throw new ECareException("Incorrect login/password or client does not exist.", nrx);
            }
            et.commit();
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
        EntityTransaction et = em.getTransaction();
        Client cl = null;
        try {
            et.begin();
            try {
                cl = clDAO.findClientByNumber(number);
            } catch (NoResultException nrx) {
                throw new ECareException("Client with number = " + number + " not found.");
            }
            et.commit();
            return cl;
        }
        catch (RuntimeException re) {
            if(et.isActive()) {
                et.rollback();
            }
            throw re;
        }
    }

    public void updateClient(Client cl) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            clDAO.updateClient(cl);
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
    public void deleteClient(long id) throws ECareException {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Client cl = clDAO.loadClient(id);
            if(cl == null) throw new ECareException("Client with id = " + id + " not exist.");
            clDAO.deleteClient(cl);
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
    public List<Client> getAllClients() {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            List<Client> clients = clDAO.getAllClients();
            tx.commit();
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
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            clDAO.deleteAllClients();
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
    public long getNumberOfClients() {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            long number = clDAO.size();
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
