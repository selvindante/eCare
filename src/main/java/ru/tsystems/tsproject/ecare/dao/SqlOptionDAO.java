package ru.tsystems.tsproject.ecare.dao;

import ru.tsystems.tsproject.ecare.entities.Option;
import ru.tsystems.tsproject.ecare.service.SqlEntityManager;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Selvin
 * on 06.10.2014.
 */
public class SqlOptionDAO extends AbstractDAO<Option> {
    private static SqlOptionDAO instance;
    private EntityManager em = SqlEntityManager.getEm();

    private SqlOptionDAO() {
    }

    public static SqlOptionDAO getInstance()
    {
        if (instance == null)
        {
            instance = new SqlOptionDAO();
        }
        return instance;
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
        Query query = em.createNamedQuery("Option.findOptionByTitleAndTariffId", Option.class);
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
        Query query = em.createNamedQuery("Option.getAllOptionsForTariff", Option.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    public void deleteAllOptionsForTariff(long id) {
        Query query = em.createNamedQuery("Option.deleteAllOptionsForTariff");
        query.setParameter(1, id);
        query.executeUpdate();
    }

    @Override
    protected long doSize() {
        Query q = em.createNamedQuery("Option.size", Option.class);
        return (Long) q.getSingleResult();
    }
}
