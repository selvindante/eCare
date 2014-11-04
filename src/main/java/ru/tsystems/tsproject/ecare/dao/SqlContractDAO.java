package ru.tsystems.tsproject.ecare.dao;

import ru.tsystems.tsproject.ecare.entities.Contract;
import ru.tsystems.tsproject.ecare.service.SqlEntityManager;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Selvin
 * on 06.10.2014.
 */
/*@Component*/
public class SqlContractDAO extends AbstractDAO<Contract> {
    private static SqlContractDAO instance;
    private EntityManager em = SqlEntityManager.getEm();

    private SqlContractDAO() {
    }

    public static SqlContractDAO getInstance()
    {
        if (instance == null) {
            instance = new SqlContractDAO();
        }
        return instance;
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
        return ((Number)em.createNamedQuery("Contract.size").getSingleResult()).longValue();
    }
}
