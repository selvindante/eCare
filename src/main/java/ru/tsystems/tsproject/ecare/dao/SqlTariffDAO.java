package ru.tsystems.tsproject.ecare.dao;

import ru.tsystems.tsproject.ecare.entities.Tariff;
import ru.tsystems.tsproject.ecare.service.SqlEntityManager;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Selvin
 * on 06.10.2014.
 */
public class SqlTariffDAO extends AbstractDAO<Tariff> {
    private static SqlTariffDAO instance;
    private EntityManager em = SqlEntityManager.getEm();

    private SqlTariffDAO() {
    }

    public static SqlTariffDAO getInstance()
    {
        if (instance == null)
        {
            instance = new SqlTariffDAO();
        }
        return instance;
    }

    @Override
    protected Tariff doSaveOrUpdate(Tariff tr) {
        return em.merge(tr);
    }

    @Override
    protected Tariff doLoad(long id) {
        return em.find(Tariff.class, id);
    }

    @Override
    protected void doDelete(Tariff tr) {
        em.remove(tr);
    }

    @Override
    protected List<Tariff> doGetAll() {
        return em.createNamedQuery("Tariff.getAllTariffs", Tariff.class).getResultList();
    }

    @Override
    protected void doDeleteAll() {
        em.createNamedQuery("Tariff.deleteAllTariffs").executeUpdate();
    }

    @Override
    protected long doSize() {
        Query q = em.createNamedQuery("Tariff.size", Tariff.class);
        return (Long) q.getSingleResult();
    }
}
