package ru.tsystems.tsproject.ecare.storage;

import ru.tsystems.tsproject.ecare.ECareException;
import ru.tsystems.tsproject.ecare.entities.Contract;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Selvin
 * on 06.10.2014.
 */
public class SqlContractDAO extends AbstractContractDAO {

    private EntityManager em = SqlEntityManager.getEm();

    @Override
    protected void doCreateContract(Contract cn) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(cn);
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
    protected Contract doLoadContract(long id) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Contract cr = em.find(Contract.class, id);
            tx.commit();
            if(cr == null)  throw new ECareException("Contract with id = " + id + " not found.", id);
            return cr;
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

    @Override
    protected void doUpdateContract(Contract cn) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(cn);
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
    protected void doDeleteContract(long id) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Contract cr = em.find(Contract.class, id);
            if(cr == null) throw new ECareException("Contract with id = " + id + " not exist.", id);
            em.remove(cr);
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
    protected List<Contract> doGetAll() {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            TypedQuery<Contract> namedQuery = em.createNamedQuery("Contract.getAll", Contract.class);
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
    protected List<Contract> doGetAllForClient(long id) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Query query = em.createQuery("SELECT c FROM Contract c WHERE c.client.id = :id");
            query.setParameter("id", id);
            tx.commit();
            return query.getResultList();
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
            em.createQuery("DELETE FROM Contract contract").executeUpdate();
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
            Query q = em.createQuery("SELECT count(contract) FROM Contract contract");
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
