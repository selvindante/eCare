package ru.tsystems.tsproject.ecare.dao;

import ru.tsystems.tsproject.ecare.entities.Tariff;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Selvin
 * on 06.10.2014.
 */
public class SqlTariffDAO extends AbstractTariffDAO {
    private EntityManager em = SqlEntityManager.getEm();

    public SqlTariffDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    protected void doCreateTariff(Tariff tr) {
        em.merge(tr);
    }

    @Override
    protected Tariff doLoadTariff(long id) {
        return em.find(Tariff.class, id);
    }

    @Override
    protected void doUpdateTariff(Tariff tr) {
        em.merge(tr);
    }

    @Override
    protected void doDeleteTariff(Tariff tr) {
        em.remove(tr);
    }

    @Override
    protected List<Tariff> doGetAllTariffs() {
        return em.createNamedQuery("Tariff.getAllTariffs", Tariff.class).getResultList();
    }

    @Override
    protected void doDeleteAllTariffs() {
        em.createNamedQuery("Tariff.deleteAllTariffs", Tariff.class).executeUpdate();
    }

    @Override
    protected long doSize() {
        Query q = em.createNamedQuery("Tariff.size", Tariff.class);
        return (Long) q.getSingleResult();
    }
}
