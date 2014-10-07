package ru.tsystems.tsproject.ecare.storage;

import ru.tsystems.tsproject.ecare.entities.Option;

import javax.persistence.EntityManager;
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
        em.getTransaction().begin();
        em.merge(op);
        em.getTransaction().commit();
    }

    @Override
    protected Option doLoadOption(long id) {
        return em.find(Option.class, id);
    }

    @Override
    protected void doUpdateOption(Option op) {
        em.getTransaction().begin();
        em.merge(op);
        em.getTransaction().commit();
    }

    @Override
    protected void doDeleteOption(long id) {
        em.getTransaction().begin();
        em.remove(doLoadOption(id));
        em.getTransaction().commit();
    }

    @Override
    protected List<Option> doGetAll() {
        TypedQuery<Option> namedQuery = em.createNamedQuery("Option.getAll", Option.class);
        return namedQuery.getResultList();
    }

    @Override
    protected void doDeleteAll() {
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Option option").executeUpdate();
        em.getTransaction().commit();
    }

    @Override
    protected long doSize() {
        em.getTransaction().begin();
        Query q = em.createQuery("SELECT count(option) FROM Option option");
        return (Long) q.getSingleResult ();
    }
}
