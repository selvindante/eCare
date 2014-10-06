package ru.tsystems.tsproject.ecare.storage;

import ru.tsystems.tsproject.ecare.entities.Contract;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Selvin
 * on 06.10.2014.
 */
public class SqlContractDAO extends AbstractContractDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ECARE");
    private EntityManager em = emf.createEntityManager();

    @Override
    protected void doCreateContract(Contract cn) {
        em.getTransaction().begin();
        em.merge(cn);
        em.getTransaction().commit();
    }

    @Override
    protected Contract doLoadContract(long id) {
        return em.find(Contract.class, id);
    }

    @Override
    protected void doUpdateContract(Contract cn) {
        em.getTransaction().begin();
        em.merge(cn);
        em.getTransaction().commit();
    }

    @Override
    protected void doDeleteContract(long id) {
        em.getTransaction().begin();
        em.remove(doLoadContract(id));
        em.getTransaction().commit();
    }

    @Override
    protected List<Contract> doGetAll() {
        TypedQuery<Contract> namedQuery = em.createNamedQuery("Contract.getAll", Contract.class);
        return namedQuery.getResultList();
    }
}
