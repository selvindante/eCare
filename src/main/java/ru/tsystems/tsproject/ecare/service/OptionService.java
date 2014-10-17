package ru.tsystems.tsproject.ecare.service;

import ru.tsystems.tsproject.ecare.ECareException;
import ru.tsystems.tsproject.ecare.ECareLogger;
import ru.tsystems.tsproject.ecare.dao.AbstractDAO;
import ru.tsystems.tsproject.ecare.dao.SqlOptionDAO;
import ru.tsystems.tsproject.ecare.entities.Option;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Selvin
 * on 10.10.2014.
 */
public class OptionService implements IOptionService {
    private static OptionService instance;
    private EntityManager em = SqlEntityManager.getEm();
    private AbstractDAO<Option> DAO = SqlOptionDAO.getInstance();
    private SqlOptionDAO opDAO = SqlOptionDAO.getInstance();
    private Logger logger = ECareLogger.getInstance().getLogger();

    private OptionService() {
    }

    public static OptionService getInstance()
    {
        if (instance == null)
        {
            instance = new OptionService();
        }
        return instance;
    }

    @Override
    public Option saveOrUpdateOption(Option op) throws ECareException {
        logger.info("Save/update option " + op + " in DB.");
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Option option = DAO.saveOrUpdate(op);
            tx.commit();
            if(option == null) {
                ECareException ecx = new ECareException("Failed to save/update option " + op + " in DB.");
                logger.info(ecx.getMessage());
                throw ecx;
            }
            logger.info("Option " + option + " saved in DB.");
            return option;
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

    @Override
    public Option loadOption(long id) throws ECareException {
        logger.info("Load option with id: " + id + " from DB.");
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Option op = DAO.load(id);
            tx.commit();
            if(op == null) {
                ECareException ecx = new ECareException("Option with id = " + id + " not found in DB.");
                logger.info(String.valueOf(ecx));
                throw ecx;
            }
            logger.info("Options " + op + " loaded from DB.");
            return op;
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

    @Override
    public Option findOptionByTitleAndTariffId(String title, long id) throws ECareException {
        logger.info("Find option with title: " + title + " and tariff id: " + id + " in DB.");
        Option op = null;
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            try {
                op = opDAO.findOptionByTitleAndTariffId(title, id);
            } catch(NoResultException nrx) {
                ECareException ecx = new ECareException("Option " + title + " not found or tariff with id: " + id + " not exist.", nrx);
                logger.info(ecx.getMessage() + "\n" + nrx.getMessage());
                throw ecx;
            }
            et.commit();
            logger.info("Option " + op + " found and loaded from DB.");
            return op;
        }
        catch (RuntimeException re) {
            if(et.isActive()) {
                et.rollback();
            }
            throw re;
        }
    }

    @Override
    public void deleteOption(long id) throws ECareException {
        logger.info("Delete option with id: " + id + " from DB.");
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Option op = DAO.load(id);
            if(op == null) {
                ECareException ecx = new ECareException("Option with id = " + id + " not exist.");
                logger.info(ecx.getMessage());
                throw ecx;
            }
            DAO.delete(op);
            tx.commit();
            logger.info("Option " + op + " deleted from DB.");
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

    @Override
    public List<Option> getAllOptions() {
        logger.info("Get all options from DB.");
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            List<Option> options = DAO.getAll();
            tx.commit();
            logger.info("All options obtained from DB.");
            return options;
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

    @Override
    public List<Option> getAllOptionsForTariff(long id) {
        logger.info("Get all options from DB for tariff with id: " + id + ".");
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            List<Option> options = opDAO.getAllOptionsForTariff(id);
            tx.commit();
            logger.info("All options for tariff id: " + id + " obtained from DB.");
            return options;
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

    @Override
    public void deleteAllOptionsForTariff(long id) {
        logger.info("Delete all options from DB for tariff with id: " + id + ".");
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            opDAO.deleteAllOptionsForTariff(id);
            tx.commit();
            logger.info("All options for tariff id: " + id + " deleted from DB.");
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

    @Override
    public long getNumberOfOptions() {
        logger.info("Get number of options in DB.");
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            long number = DAO.size();
            tx.commit();
            logger.info(number + "of options obtained from DB.");
            return number;
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

    @Override
    public void setDependentOption(Option currentOption, Option dependentOption) throws ECareException {
        logger.info("Set dependency of option id: " + currentOption.getId() + " with option id: " + dependentOption.getId() + ".");
        if(!currentOption.getIncompatibleOptions().contains(dependentOption)) {
            if(!currentOption.getDependentOptions().contains(dependentOption) && !currentOption.equals(dependentOption)) {
                currentOption.addDependentOption(dependentOption);
                logger.info("Option id: " + dependentOption.getId() + " is now dependent on option id: " + currentOption.getId() + ".");
                dependentOption.addDependentOption(currentOption);
                logger.info("Option id: " + currentOption.getId() + " is now dependent on option id: " + dependentOption.getId() + ".");
            }
            else {
                ECareException ecx = new ECareException("Option " + currentOption.getId() + " already contains such dependence.");
                logger.info(ecx.getMessage());
                throw ecx;
            }
        }
        else {
            ECareException ecx = new ECareException("These options are incompatible.");
            logger.info(ecx.getMessage());
            throw ecx;
        }
    }

    @Override
    public void deleteDependentOption(Option currentOption, Option dependentOption) throws ECareException {
        logger.info("Remove dependency of option id: " + currentOption.getId() + " with option id: " + dependentOption.getId() + ".");
        if(currentOption.getDependentOptions().contains(dependentOption)) {
            currentOption.deleteDependentOption(dependentOption);
            logger.info("Option id: " + dependentOption.getId() + " is now independent from option id: " + currentOption.getId() + ".");
            dependentOption.deleteDependentOption(currentOption);
            logger.info("Option id: " + currentOption.getId() + " is now independent from option id: " + dependentOption.getId() + ".");
        }
        else {
            ECareException ecx = new ECareException("Option " + currentOption.getId() + " not contains such dependence.");
            logger.info(ecx.getMessage());
            throw ecx;
        }
    }

    @Override
    public void setIncompatibleOption(Option currentOption, Option incompatibleOption) throws ECareException {
        logger.info("Set incompatibility of option id: " + currentOption.getId() + " with option id: " + incompatibleOption.getId() + ".");
        if(!currentOption.getDependentOptions().contains(incompatibleOption)) {
            if(!currentOption.getIncompatibleOptions().contains(incompatibleOption) && !currentOption.equals(incompatibleOption)) {
                currentOption.addIncompatibleOption(incompatibleOption);
                logger.info("Option id: " + currentOption.getId() + " is now incompatible with option id: " + incompatibleOption.getId() + ".");
                incompatibleOption.addIncompatibleOption(currentOption);
                logger.info("Option id: " + incompatibleOption.getId() + " is now incompatible with option id: " + currentOption.getId() + ".");
            }
            else {
                ECareException ecx = new ECareException("Option " + currentOption.getId() + " already contains such incompatibility.");
                logger.info(ecx.getMessage());
                throw ecx;
            }
        }
        else {
            ECareException ecx = new ECareException("These options are incompatible.");
            logger.info(ecx.getMessage());
            throw ecx;
        }
    }

    @Override
    public void deleteIncompatibleOption(Option currentOption, Option incompatibleOption) throws ECareException {
        logger.info("Remove incompatibility of option id: " + currentOption.getId() + " with option id: " + incompatibleOption.getId() + ".");
        if(currentOption.getIncompatibleOptions().contains(incompatibleOption)) {
            currentOption.deleteIncompatibleOption(incompatibleOption);
            logger.info("Option id: " + currentOption.getId() + " is not incompatible now with option id: " + incompatibleOption.getId() + ".");
            incompatibleOption.deleteIncompatibleOption(currentOption);
            logger.info("Option id: " + incompatibleOption.getId() + " is not incompatible now with option id: " + currentOption.getId() + ".");
        }
        else {
            ECareException ecx = new ECareException("Option " + currentOption.getId() + " not contains such incompatibility.");
            logger.info(ecx.getMessage());
            throw ecx;
        }
    }
}
