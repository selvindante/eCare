package ru.tsystems.tsproject.ecare.dao;

import ru.tsystems.tsproject.ecare.entities.Client;
import ru.tsystems.tsproject.ecare.service.SqlEntityManager;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Selvin
 * on 02.10.2014.
 */
public class SqlClientDAO extends AbstractDAO<Client> {
    private static SqlClientDAO instance;
    private EntityManager em = SqlEntityManager.getEm();

    private SqlClientDAO() {
    }

    public static SqlClientDAO getInstance()
    {
        if (instance == null)
        {
            instance = new SqlClientDAO();
        }
        return instance;
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
        Query query = em.createNamedQuery("Client.findClientByLoginAndPassword", Client.class);
        query.setParameter("login", login);
        query.setParameter("password", password);
        return (Client) query.getSingleResult();
    }

    public Client findClientByNumber(long number) {
        Query query = em.createNamedQuery("Client.findClientByNumber", Client.class);
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
        em.createNamedQuery("Client.deleteAllClients").executeUpdate();
    }

    @Override
    protected long doSize() {
        return ((Number)em.createNamedQuery("Client.size").getSingleResult()).longValue();
    }

    public Client findClientByLogin(String login) {
        Query query = em.createNamedQuery("Client.findClientByLogin", Client.class);
        query.setParameter("login", login);
        return (Client) query.getSingleResult();
    }
}
