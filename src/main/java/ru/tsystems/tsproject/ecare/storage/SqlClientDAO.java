package ru.tsystems.tsproject.ecare.storage;

import ru.tsystems.tsproject.ecare.entities.Client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Selvin
 * on 02.10.2014.
 */
public class SqlClientDAO extends AbstractClientDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ECARE");
    private EntityManager em = emf.createEntityManager();

    @Override
    protected void doCreateClient(Client cl) {
        em.getTransaction().begin();
        em.merge(cl);
        em.getTransaction().commit();
    }

    @Override
    protected Client doLoadClient(long id) {
        return em.find(Client.class, id);
    }

    @Override
    protected void doUpdateClient(Client cl) {
        em.getTransaction().begin();
        em.merge(cl);
        em.getTransaction().commit();
    }

    @Override
    protected void doDeleteClient(long id) {
        em.getTransaction().begin();
        em.remove(doLoadClient(id));
        em.getTransaction().commit();
    }

    @Override
    protected List<Client> doGetAll() {
        TypedQuery<Client> namedQuery = em.createNamedQuery("Client.getAll", Client.class);
        return namedQuery.getResultList();
    }
}
