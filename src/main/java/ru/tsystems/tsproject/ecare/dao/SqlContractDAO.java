package ru.tsystems.tsproject.ecare.dao;

import ru.tsystems.tsproject.ecare.entities.Contract;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Selvin
 * on 06.10.2014.
 */
public class SqlContractDAO extends AbstractContractDAO {

    private EntityManager em;

    public SqlContractDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    protected void doCreateContract(Contract cn) {
        em.merge(cn);
    }

    @Override
    protected Contract doLoadContract(long id) {
        return em.find(Contract.class, id);
    }

    @Override
    protected void doUpdateContract(Contract cn) {
        em.merge(cn);
    }

    @Override
    protected void doDeleteContract(Contract cn) {
        em.remove(cn);
    }

    @Override
    protected List<Contract> doGetAllContracts() {
        return em.createNamedQuery("Contract.getAllContracts", Contract.class).getResultList();
    }

    @Override
    protected List<Contract> doGetAllContractsForClient(long id) {
        Query query = em.createQuery("SELECT c FROM Contract c WHERE c.client.id = :id");
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    protected void doDeleteAllContracts() {
        em.createNamedQuery("Contract.deleteAllContracts", Contract.class).executeUpdate();
    }

    @Override
    protected long doSize() {
        Query q = em.createNamedQuery("Contract.size", Contract.class);
        return (Long) q.getSingleResult();
    }
}
