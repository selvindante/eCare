package ru.tsystems.tsproject.ecare.dao;

import ru.tsystems.tsproject.ecare.entities.Client;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Selvin
 * on 02.10.2014.
 */
public class SqlClientDAO extends AbstractDAO<Client> {
    private EntityManager em;

    public SqlClientDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    protected Client doSaveOrUpdate(Client cl) {
        return em.merge(cl);
    }

    @Override
    protected Client doLoad(long id) {
        return em.find(Client.class, id);
    }

    public Client findClientByLoginAndPassword(String login, String password) {
        Query query = em.createQuery("SELECT c FROM Client c WHERE c.email = :login AND c.password = :password");
        query.setParameter("login", login);
        query.setParameter("password", password);
        return (Client) query.getSingleResult();
    }

    public Client findClientByNumber(long number) {
        Query query = em.createQuery("SELECT cn.client FROM Contract cn WHERE cn.number = :number");
        query.setParameter("number", number);
        return (Client) query.getSingleResult();
    }

    @Override
    protected void doDelete(Client cl) {
        em.remove(cl);
    }

    @Override
    protected List<Client> doGetAll() {
        return em.createNamedQuery("Client.getAllClients", Client.class).getResultList();
    }

    @Override
    protected void doDeleteAll() {
        em.createQuery("DELETE FROM Client c WHERE c.role = 'client'");
    }

    @Override
    protected long doSize() {
        Query q = em.createNamedQuery("Client.size", Client.class);
        return (Long) q.getSingleResult();
    }
}
