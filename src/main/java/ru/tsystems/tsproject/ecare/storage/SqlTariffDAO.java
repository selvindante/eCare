package ru.tsystems.tsproject.ecare.storage;

import ru.tsystems.tsproject.ecare.entities.Tariff;

import javax.persistence.EntityManager;
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
        em.getTransaction().begin();
        em.merge(tr);
        em.getTransaction().commit();
    }

    @Override
    protected Tariff doLoadTariff(long id) {
        return em.find(Tariff.class, id);
    }

    @Override
    protected void doUpdateTariff(Tariff tr) {
        em.getTransaction().begin();
        em.merge(tr);
        em.getTransaction().commit();
    }

    @Override
    protected void doDeleteTariff(long id) {
        em.getTransaction().begin();
        em.remove(doLoadTariff(id));
        em.getTransaction().commit();
    }

    @Override
    protected List<Tariff> doGetAll() {
        TypedQuery<Tariff> namedQuery = em.createNamedQuery("Tariff.getAll", Tariff.class);
        return namedQuery.getResultList();
    }
}
