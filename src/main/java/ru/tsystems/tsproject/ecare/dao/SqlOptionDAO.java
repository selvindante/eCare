package ru.tsystems.tsproject.ecare.dao;

import ru.tsystems.tsproject.ecare.entities.Option;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Selvin
 * on 06.10.2014.
 */
public class SqlOptionDAO extends AbstractDAO<Option> {
    private EntityManager em;

    public SqlOptionDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    protected Option doSaveOrUpdate(Option op) {
        return em.merge(op);
    }

    @Override
    protected Option doLoad(long id) {
        return em.find(Option.class, id);
    }

    public Option findOptionByTitleAndTariffId(String title, long id) {
        Query query = em.createQuery("SELECT o FROM Option o WHERE o.title = :title AND o.tariff.id = :id");
        query.setParameter("title", title);
        query.setParameter("id", id);
        return (Option) query.getSingleResult();
    }

    @Override
    protected void doDelete(Option op) {
        em.remove(op);
    }

    @Override
    protected List<Option> doGetAll() {
        return em.createNamedQuery("Option.getAllOptions", Option.class).getResultList();
    }

    @Override
    protected void doDeleteAll() {

    }

    public List<Option> getAllOptionsForTariff(long id) {
        Query query = em.createQuery("SELECT o FROM Option o WHERE o.tariff.id = :id");
        query.setParameter("id", id);
        return query.getResultList();
    }

    public void deleteAllOptionsForTariff(long id) {
        Query query = em.createQuery("DELETE FROM Option o WHERE o.tariff.id = :id");
        query.setParameter("id", id);
    }

    @Override
    protected long doSize() {
        Query q = em.createNamedQuery("Option.size", Option.class);
        return (Long) q.getSingleResult();
    }
}
