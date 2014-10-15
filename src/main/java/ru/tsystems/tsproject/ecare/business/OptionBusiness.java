package ru.tsystems.tsproject.ecare.business;

import ru.tsystems.tsproject.ecare.ECareException;
import ru.tsystems.tsproject.ecare.dao.AbstractOptionDAO;
import ru.tsystems.tsproject.ecare.dao.SqlEntityManager;
import ru.tsystems.tsproject.ecare.dao.SqlOptionDAO;
import ru.tsystems.tsproject.ecare.entities.Option;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import java.util.List;

/**
 * Created by Selvin
 * on 10.10.2014.
 */
public class OptionBusiness {
    private EntityManager em = SqlEntityManager.getEm();
    private AbstractOptionDAO opDAO = new SqlOptionDAO(em);

    public void createOption(Option op) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            opDAO.createOption(op);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

    public Option loadOption(long id) throws ECareException {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Option op = opDAO.loadOption(id);
            tx.commit();
            if(op == null)  throw new ECareException("Option with id = " + id + " not found.");
            return op;
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

    public Option findOptionByTitleAndTariffId(String title, long id) throws ECareException {
        Option op = null;
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            try {
                op = opDAO.findOptionByTitleAndTariffId(title, id);
            } catch(NoResultException nrex) {
                throw new ECareException("Option " + title + " not found or tariff with id: " + id + " not exist.", nrex);
            }
            et.commit();
            return op;
        }
        catch (RuntimeException re) {
            if(et.isActive()) {
                et.rollback();
            }
            throw re;
        }
    }

    public void updateOption(Option op) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            opDAO.updateOption(op);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

    public void deleteOption(long id) throws ECareException {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Option op = opDAO.loadOption(id);
            if(op == null) throw new ECareException("Option with id = " + id + " not exist.");
            opDAO.deleteOption(op);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

    public List<Option> getAllOptions() {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            List<Option> options = opDAO.getAllOptions();
            tx.commit();
            return options;
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

    public List<Option> getAllOptionsForTariff(long id) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            List<Option> options = opDAO.getAllOptionsForTariff(id);
            tx.commit();
            return options;
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

    public void deleteAllOptionsForTariff(long id) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            opDAO.deleteAllOptionsForTariff(id);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

    public long getNumberOfOptions() {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            long number = opDAO.size();
            tx.commit();
            return number;
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

    public void setDependentOption(Option currentOption, Option dependentOption) throws ECareException {
        if(!currentOption.getIncompatibleOptions().contains(dependentOption)) {
            if(!currentOption.getDependentOptions().contains(dependentOption) && !currentOption.equals(dependentOption)) {
                currentOption.addDependentOption(dependentOption);
                dependentOption.addDependentOption(currentOption);
            }
            else throw new ECareException("Option " + currentOption.getId() + " already contains such dependence.");
        }
        else throw new ECareException("These options are incompatible.");
    }

    public void deleteDependentOption(Option currentOption, Option dependentOption) throws ECareException {
        if(currentOption.getDependentOptions().contains(dependentOption)) {
            currentOption.deleteDependentOption(dependentOption);
            dependentOption.deleteDependentOption(currentOption);
        }
        else throw new ECareException("Option " + currentOption.getId() + " not contains such dependence.");
    }

    public void setIncompatibleOption(Option currentOption, Option incompatibleOption) throws ECareException {
        if(!currentOption.getDependentOptions().contains(incompatibleOption)) {
            if(!currentOption.getIncompatibleOptions().contains(incompatibleOption) && !currentOption.equals(incompatibleOption)) {
                currentOption.addIncompatibleOption(incompatibleOption);
                incompatibleOption.addIncompatibleOption(currentOption);
            }
            else throw new ECareException("Option " + currentOption.getId() + " already contains such dependence.");
        }
        else throw new ECareException("These options are dependent.");
    }

    public void deleteIncompatibleOption(Option currentOption, Option incompatibleOption) throws ECareException {
        if(currentOption.getIncompatibleOptions().contains(incompatibleOption)) {
            currentOption.deleteIncompatibleOption(incompatibleOption);
            incompatibleOption.deleteIncompatibleOption(currentOption);
        }
        else throw new ECareException("Option " + currentOption.getId() + " not contains such incompatibility.");
    }
}
