package ru.tsystems.tsproject.ecare.storage;

import ru.tsystems.tsproject.ecare.ECareException;
import ru.tsystems.tsproject.ecare.entities.Tariff;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Selvin
 * on 06.10.2014.
 */
public class SqlTariffDAO extends AbstractTariffDAO {

    private EntityManager em = SqlEntityManager.getEm();

    @Override
    protected void doCreateTariff(Tariff tr) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(tr);
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
    protected Tariff doLoadTariff(long id) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Tariff tr = em.find(Tariff.class, id);
            tx.commit();
            if(tr == null)  throw new ECareException("Tariff with id = " + id + " not found.", id);
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
    protected void doUpdateTariff(Tariff tr) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(tr);
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
    protected void doDeleteTariff(long id) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Tariff tr = em.find(Tariff.class, id);
            if(tr == null) throw new ECareException("Tariff with id = " + id + " not exist.", id);
            em.remove(tr);
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
    protected List<Tariff> doGetAll() {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            TypedQuery<Tariff> namedQuery = em.createNamedQuery("Tariff.getAll", Tariff.class);
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
            em.createQuery("DELETE FROM Tariff tariff").executeUpdate();
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
            Query q = em.createQuery("SELECT count(tariff) FROM Tariff tariff");
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
