package ru.tsystems.tsproject.ecare.storage;

import ru.tsystems.tsproject.ecare.ECareException;
import ru.tsystems.tsproject.ecare.entities.Option;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Selvin
 * on 06.10.2014.
 */
public class SqlOptionDAO extends AbstractOptionDAO {

    private EntityManager em = SqlEntityManager.getEm();

    @Override
    protected void doCreateOption(Option op) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(op);
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
    protected Option doLoadOption(long id) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Option op = em.find(Option.class, id);
            tx.commit();
            if(op == null)  throw new ECareException("Option with id = " + id + " not found.", id);
            return op;
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

    @Override
    protected void doUpdateOption(Option op) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(op);
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
    protected void doDeleteOption(long id) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Option op = em.find(Option.class, id);
            if(op == null) throw new ECareException("Option with id = " + id + " not exist.", id);
            em.remove(op);
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
    protected List<Option> doGetAll() {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            TypedQuery<Option> namedQuery = em.createNamedQuery("Option.getAll", Option.class);
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
    protected List<Option> doGetAllForTariff(long id) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Query query = em.createQuery("SELECT o FROM Option o WHERE o.tariff.id = :id");
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
            em.createQuery("DELETE FROM Option option").executeUpdate();
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
            Query q = em.createQuery("SELECT count(option) FROM Option option");
            tx.commit();
            return (Long) q.getSingleResult();
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }
}
