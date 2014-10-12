package ru.tsystems.tsproject.ecare.dao;

import ru.tsystems.tsproject.ecare.entities.Client;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Selvin
 * on 02.10.2014.
 */
public class SqlClientDAO extends AbstractClientDAO {
    private EntityManager em;

    public SqlClientDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    protected void doCreateClient(Client cl) {
        em.merge(cl);
    }

    @Override
    protected Client doLoadClient(long id) {
        return em.find(Client.class, id);
    }

    @Override
    protected Client doFindClientByLoginAndPass(String login, String password) {
        Query query = em.createQuery("SELECT c FROM Client c WHERE c.email = :login AND c.password = :password");
        query.setParameter("login", login);
        query.setParameter("password", password);
        return (Client) query.getSingleResult();
    }

    @Override
    protected Client doFindClientByNumber(long number) {
        Query query = em.createQuery("SELECT cn.client FROM Contract cn WHERE cn.number = :number");
        query.setParameter("number", number);
        return (Client) query.getSingleResult();
    }

    @Override
    protected void doUpdateClient(Client cl) {
        em.merge(cl);
    }

    @Override
    protected void doDeleteClient(Client cl) {
        em.remove(cl);
    }

    @Override
    protected List<Client> doGetAllClients() {
        return em.createNamedQuery("Client.getAllClients", Client.class).getResultList();
    }

    @Override
    protected void doDeleteAllClients() {
        em.createNamedQuery("Client.deleteAllClients", Client.class).executeUpdate();
    }

    @Override
    protected long doSize() {
        Query q = em.createNamedQuery("Client.size", Client.class);
        return (Long) q.getSingleResult();
    }
}
