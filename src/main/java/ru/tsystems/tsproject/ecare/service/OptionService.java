package ru.tsystems.tsproject.ecare.service;

import org.apache.log4j.Logger;
import ru.tsystems.tsproject.ecare.ECareException;
import ru.tsystems.tsproject.ecare.dao.AbstractDAO;
import ru.tsystems.tsproject.ecare.dao.SqlOptionDAO;
import ru.tsystems.tsproject.ecare.entities.Option;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * This class is the implementation of IOptionService for working with option DAO
 * and option entities. Class OptionService is a singleton.
 *
 * @author Starostin Konstantin
 * @see ru.tsystems.tsproject.ecare.service.IOptionService
 */
public class OptionService implements IOptionService {

    /*Instance of the singleton class*/
    private static OptionService instance;

    /*Entity manager for working with JPA methods*/
    private EntityManager em = SqlEntityManager.getEm();

    /*SQL option implementations of abstract DAO class*/
    private AbstractDAO<Option> DAO = SqlOptionDAO.getInstance();
    private SqlOptionDAO opDAO = SqlOptionDAO.getInstance();

    /*Logger for option service operations*/
    private static Logger logger = Logger.getLogger("OptionService");

    /*Private constructor of singleton class*/
    private OptionService() {
    }

    /**
     * This method return instance of singleton class OptionService.
     *
     * @return instance of class.
     */
    public static OptionService getInstance()
    {
        if (instance == null)
        {
            instance = new OptionService();
        }
        return instance;
    }

    /**
     * This method implements saving or updating of option in the database.
     *
     * @param op option entity to be saved or updated.
     * @return saved or updated option entity.
     * @throws ECareException if an error occurred during saving or updating of entity
     * and DAO returns null.
     */
    @Override
    public Option saveOrUpdateOption(Option op) throws ECareException {
        logger.info("Save/update option " + op + " in DB.");
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Option option = DAO.saveOrUpdate(op);
            tx.commit();
            //If DAO returns null method will throws an ECareException
            if(option == null) {
                ECareException ecx = new ECareException("Failed to save/update option " + op + " in DB.");
                logger.error(ecx.getMessage(), ecx);
                throw ecx;
            }
            logger.info("Option " + option + " saved in DB.");
            //else option will be saved and method returns option entity
            return option;
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

    /**
     * This method implements loading of option from the database.
     *
     * @param id option id for search that option in the database.
     * @return loaded option entity.
     * @throws ECareException if an error occurred during loading of entity
     * and DAO returns null.
     */
    @Override
    public Option loadOption(long id) throws ECareException {
        logger.info("Load option with id: " + id + " from DB.");
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Option op = DAO.load(id);
            tx.commit();
            //If DAO returns null method will throws an ECareException
            if(op == null) {
                ECareException ecx = new ECareException("Option with id = " + id + " not found in DB.");
                logger.warn(String.valueOf(ecx), ecx);
                throw ecx;
            }
            logger.info("Options " + op + " loaded from DB.");
            //else method returns option entity
            return op;
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

    /**
     * This method implements deleting of option from the database.
     *
     * @param id option id for deleting that option from the database.
     * @throws ECareException if an error occurred during intermediate loading
     * of entity and DAO returns null.
     */
    @Override
    public void deleteOption(long id) throws ECareException {
        logger.info("Delete option with id: " + id + " from DB.");
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Option op = DAO.load(id);
            //If DAO returns null method will throws an ECareException
            if(op == null) {
                ECareException ecx = new ECareException("Option with id = " + id + " not exist.");
                logger.warn(ecx.getMessage(), ecx);
                throw ecx;
            }
            // Else option will be deleted from the database.
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

    /**
     * This method implements receiving of all options from the database.
     *
     * @return list of received options.
     * @throws ECareException if an error occurred during receiving of entities
     * and DAO returns null.
     */
    @Override
    public List<Option> getAllOptions() throws ECareException {
        logger.info("Get all options from DB.");
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            List<Option> options = DAO.getAll();
            tx.commit();
            //If DAO returns null method will throws an ECareException
            if(options == null) {
                ECareException ecx = new ECareException("Failed to get all options from DB.");
                logger.error(ecx.getMessage(), ecx);
                throw ecx;
            }
            logger.info("All options obtained from DB.");
            // Else method returns list of option entities
            return options;
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

    /**
     * This method implements receiving of all options for tariff from the database.
     *
     * @param id contract id for searching of all options for this contract.
     * @return list of received options.
     * @throws ECareException if an error occurred during receiving of entities
     * and DAO returns null.
     */
    @Override
    public List<Option> getAllOptionsForTariff(long id) throws ECareException{
        logger.info("Get all options from DB for tariff with id: " + id + ".");
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            List<Option> options = opDAO.getAllOptionsForTariff(id);
            tx.commit();
            //If DAO returns null method will throws an ECareException
            if(options == null) {
                ECareException ecx = new ECareException("Failed to get all options from DB for tariff id: " + id + ".");
                logger.error(ecx.getMessage(), ecx);
                throw ecx;
            }
            logger.info("All options for tariff id: " + id + " obtained from DB.");
            // Else method returns list of option entities
            return options;
        }
        catch (RuntimeException re) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw re;
        }
    }

    /**
     * This method implements deleting of all options for tariff from the database.
     *
     * @param id tariff id for deleting of all options for this tariff.
     */
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

    /**
     * This method implements receiving number of all options from the database.
     *
     * @return number of options in the storage.
     */
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

    /**
     * This method sets dependent option for current option.
     *
     * @param currentOption current option entity.
     * @param dependentOption option entity which must be sets as dependent for
     *                        current option.
     * @return current option entity with dependent option in list.
     * @throws ECareException if this options already incompatible.
     */
    @Override
    public Option setDependentOption(Option currentOption, Option dependentOption) throws ECareException {
        logger.info("Set dependency of option id: " + currentOption.getId() + " with option id: " + dependentOption.getId() + ".");
        // If current option not incompatible for chosen option.
        if(!currentOption.getIncompatibleOptions().contains(dependentOption)) {
            // If current option not linked with chosen option by dependency or current option is not chosen option.
            if(!currentOption.getDependentOptions().contains(dependentOption) && !currentOption.equals(dependentOption)) {
                currentOption.addDependentOption(dependentOption);
                logger.info("Option id: " + dependentOption.getId() + " is now dependent for option id: " + currentOption.getId() + ".");
                currentOption = saveOrUpdateOption(currentOption);
            }
        }
        else {
            ECareException ecx = new ECareException("Chosen options are incompatible.");
            logger.warn(ecx.getMessage(), ecx);
            throw ecx;
        }
        return currentOption;
    }

    /**
     * This method removes dependent option for current option.
     *
     * @param currentOption current option entity.
     * @param dependentOption option entity which must be removed from list of
     *                        dependent options for current option.
     * @return current option entity without dependent option in list.
     * @throws ECareException if current option not contains such dependence.
     */
    @Override
    public Option deleteDependentOption(Option currentOption, Option dependentOption) throws ECareException {
        logger.info("Remove dependency of option id: " + currentOption.getId() + " with option id: " + dependentOption.getId() + ".");
        // If current option linked with chosen option by dependency.
        if(currentOption.getDependentOptions().contains(dependentOption)) {
            currentOption.deleteDependentOption(dependentOption);
            logger.info("Option id: " + dependentOption.getId() + " is now independent from option id: " + currentOption.getId() + ".");
            currentOption = saveOrUpdateOption(currentOption);
        }
        else {
            ECareException ecx = new ECareException("Option " + currentOption.getId() + " not contains such dependence.");
            logger.warn(ecx.getMessage(), ecx);
            throw ecx;
        }
        return currentOption;
    }

    /**
     * This method removes all dependent options for current option.
     *
     * @param currentOption current option entity.
     */
    @Override
    public void clearDependentOptions(Option currentOption) {
        logger.info("Remove all dependent options from option id: " + currentOption.getId() + ".");
        for (Option o: currentOption.getDependentOptions()) {
            // For every dependent option for current option: remove dependency.
                deleteDependentOption(o, currentOption);
                saveOrUpdateOption(o);
            }
        // Remove all dependent options for current option.
        currentOption.getDependentOptions().clear();
        logger.info("All dependent options removed from option id: " + currentOption.getId() + ".");
    }

    /**
     * This method sets incompatible option for current option.
     *
     * @param currentOption current option entity.
     * @param incompatibleOption option entity which must be sets as incompatible for
     *                        current option.
     * @return current option entity with incompatible option in list.
     * @throws ECareException if this options already dependent.
     */
    @Override
    public Option setIncompatibleOption(Option currentOption, Option incompatibleOption) throws ECareException {
        logger.info("Set incompatibility of option id: " + currentOption.getId() + " with option id: " + incompatibleOption.getId() + ".");
        // If current option not dependent for chosen option.
        if(!currentOption.getDependentOptions().contains(incompatibleOption)) {
            // If current option not linked with chosen option by incompatibility or current option is not chosen option.
            if(!currentOption.getIncompatibleOptions().contains(incompatibleOption) && !currentOption.equals(incompatibleOption)) {
                currentOption.addIncompatibleOption(incompatibleOption);
                logger.info("Option id: " + currentOption.getId() + " is now incompatible with option id: " + incompatibleOption.getId() + ".");
                currentOption = saveOrUpdateOption(currentOption);
            }
        }
        else {
            ECareException ecx = new ECareException("Chosen options are dependent.");
            logger.warn(ecx.getMessage(), ecx);
            throw ecx;
        }
        return currentOption;
    }

    /**
     * This method removes incompatible option for current option.
     *
     * @param currentOption current option entity.
     * @param incompatibleOption option entity which must be removed from list of
     *                        incompatible options for current option.
     * @return current option entity without incompatible option in list.
     * @throws ECareException if current option not contains such incompatibility.
     */
    @Override
    public Option deleteIncompatibleOption(Option currentOption, Option incompatibleOption) throws ECareException {
        logger.info("Remove incompatibility of option id: " + currentOption.getId() + " with option id: " + incompatibleOption.getId() + ".");
        // If current option linked with chosen option by incompatibility.
        if(currentOption.getIncompatibleOptions().contains(incompatibleOption)) {
            currentOption.deleteIncompatibleOption(incompatibleOption);
            logger.info("Option id: " + currentOption.getId() + " is not incompatible now with option id: " + incompatibleOption.getId() + ".");
            currentOption = saveOrUpdateOption(currentOption);
        }
        else {
            ECareException ecx = new ECareException("Option " + currentOption.getId() + " not contains such incompatibility.");
            logger.warn(ecx.getMessage(), ecx);
            throw ecx;
        }
        return currentOption;
    }

    /**
     * This method removes all incompatible options for current option.
     *
     * @param currentOption current option entity.
     */
    @Override
    public void clearIncompatibleOptions(Option currentOption) {
        logger.info("Remove all incompatible options from option id: " + currentOption.getId() + ".");
        for (Option o: currentOption.getIncompatibleOptions()) {
            // For every incompatible option for current option: remove incompatibility.
            deleteIncompatibleOption(o, currentOption);
            saveOrUpdateOption(o);
        }
        // Remove all incompatible options for current option.
        currentOption.getIncompatibleOptions().clear();
        logger.info("All incompatible options removed from option id: " + currentOption.getId() + ".");
    }
}
