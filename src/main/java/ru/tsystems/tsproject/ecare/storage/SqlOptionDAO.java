package ru.tsystems.tsproject.ecare.storage;

import ru.tsystems.tsproject.ecare.entities.Option;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Selvin
 * on 06.10.2014.
 */
public class SqlOptionDAO extends AbstractOptionDAO {

    private EntityManager em;

    public SqlOptionDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    protected void doCreateOption(Option op) {
        em.merge(op);
    }

    @Override
    protected Option doLoadOption(long id) {
        return em.find(Option.class, id);
    }

    @Override
    protected void doUpdateOption(Option op) {
        em.merge(op);
    }

    @Override
    protected void doDeleteOption(Option op) {
        em.remove(op);
    }

    @Override
    protected List<Option> doGetAllOptions() {
        return em.createNamedQuery("Option.getAllOptions", Option.class).getResultList();
    }

    @Override
    protected List<Option> doGetAllOptionsForTariff(long id) {
        Query query = em.createQuery("SELECT o FROM Option o WHERE o.tariff.id = :id");
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    protected void doDeleteAllOptions() {
        em.createNamedQuery("Option.deleteAllOptions", Option.class).executeUpdate();
    }

    @Override
    protected long doSize() {
        Query q = em.createNamedQuery("Option.size", Option.class);
        return (Long) q.getSingleResult();
    }
}
