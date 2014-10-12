package ru.tsystems.tsproject.ecare.business;

import ru.tsystems.tsproject.ecare.ECareException;
import ru.tsystems.tsproject.ecare.entities.Option;
import ru.tsystems.tsproject.ecare.dao.AbstractOptionDAO;
import ru.tsystems.tsproject.ecare.dao.SqlEntityManager;
import ru.tsystems.tsproject.ecare.dao.SqlOptionDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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

    public void deleteAllOptions() {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            opDAO.deleteAllOptions();
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
            if(!currentOption.getDependentOptions().contains(dependentOption)) {
                currentOption.addDependentOption(dependentOption);
                //dependentOption.addDependentOption(currentOption); ??????
            }
            else throw new ECareException("Option " + currentOption.getId() + " already contains such dependence.");
        }
        else throw new ECareException("These options are incompatible.");
    }

    public void deleteDependentOption(Option currentOption, Option dependentOption) throws ECareException {
        if(currentOption.getDependentOptions().contains(dependentOption)) {
            currentOption.deleteDependentOption(dependentOption);
        }
        else throw new ECareException("Option " + currentOption.getId() + " not contains such dependence.");
    }

    public void setIncompatibleOption(Option currentOption, Option incompatibleOption) throws ECareException {
        if(!currentOption.getDependentOptions().contains(incompatibleOption)) {
            if(!currentOption.getIncompatibleOptions().contains(incompatibleOption)) {
                currentOption.addIncompatibleOption(incompatibleOption);
            }
            else throw new ECareException("Option " + currentOption.getId() + " already contains such dependence.");
        }
        else throw new ECareException("These options are dependent.");
    }

    public void deleteIncompatibleOption(Option currentOption, Option incompatibleOption) throws ECareException {
        if(currentOption.getIncompatibleOptions().contains(incompatibleOption)) {
            currentOption.deleteIncompatibleOption(incompatibleOption);
        }
        else throw new ECareException("Option " + currentOption.getId() + " not contains such incompatibility.");
    }
}
