package ru.tsystems.tsproject.ecare.storage;

import ru.tsystems.tsproject.ecare.ECareException;
import ru.tsystems.tsproject.ecare.entities.Client;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Selvin
 * on 02.10.2014.
 */
public class SqlClientDAO extends AbstractClientDAO {

    private EntityManager em = SqlEntityManager.getEm();

    @Override
    protected void doCreateClient(Client cl) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(cl);
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
    protected Client doLoadClient(long id) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Client cl = em.find(Client.class, id);
            tx.commit();
            if(cl == null)  throw new ECareException("Client with id = " + id + " not found.", id);
            return cl;
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

    @Override
    protected void doUpdateClient(Client cl) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(cl);
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
    protected void doDeleteClient(long id) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Client cl = em.find(Client.class, id);
            if(cl == null) throw new ECareException("Client with id = " + id + " not exist.", id);
            em.remove(cl);
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
    protected List<Client> doGetAll() {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            TypedQuery<Client> namedQuery = em.createNamedQuery("Client.getAll", Client.class);
            tx.commit();
            return namedQuery.getResultList();
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

    @Override
    protected void doDeleteAll() {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.createQuery("DELETE FROM Client client").executeUpdate();
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
    protected long doSize() {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Query q = em.createQuery("SELECT count(client) FROM Client client");
            tx.commit();
            return (Long) q.getSingleResult ();
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }
}
