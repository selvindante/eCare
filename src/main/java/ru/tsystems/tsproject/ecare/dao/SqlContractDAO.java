package ru.tsystems.tsproject.ecare.dao;

import ru.tsystems.tsproject.ecare.entities.Contract;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Selvin
 * on 06.10.2014.
 */
public class SqlContractDAO extends AbstractDAO<Contract> {
    private EntityManager em;

    public SqlContractDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    protected Contract doSaveOrUpdate(Contract cn) {
        return em.merge(cn);
    }

    @Override
    protected Contract doLoad(long id) {
        return em.find(Contract.class, id);
    }

    public Contract findContractByNumber(long number) {
        Query query = em.createNamedQuery("Contract.findContractByNumber", Contract.class);
        query.setParameter("number", number);
        return (Contract) query.getSingleResult();
    }

    @Override
    protected void doDelete(Contract cn) {
        em.remove(cn);
    }

    @Override
    protected List<Contract> doGetAll() {
        return em.createNamedQuery("Contract.getAllContracts", Contract.class).getResultList();
    }

    public List<Contract> getAllContractsForClient(long id) {
        Query query = em.createNamedQuery("Contract.getAllContractsForClient", Contract.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    protected void doDeleteAll() {
        em.createNamedQuery("Contract.deleteAllContracts").executeUpdate();
    }

    public void deleteAllContractsForClient(long id) {
        Query query = em.createNamedQuery("Contract.deleteAllContractsForClient");
        query.setParameter(1, id);
        query.executeUpdate();
    }

    @Override
    protected long doSize() {
        Query q = em.createNamedQuery("Contract.size", Contract.class);
        return (Long) q.getSingleResult();
    }
}
