package ru.tsystems.tsproject.ecare.dao;

import ru.tsystems.tsproject.ecare.entities.Option;

import java.util.List;

/**
 * Created by Selvin
 * on 06.10.2014.
 */
public abstract class AbstractOptionDAO {
    protected abstract void doCreateOption(Option op);
    protected abstract Option doLoadOption(long id);
    protected abstract Option doFindOptionByTitleAndTariffId(String title, long id);
    protected abstract void doUpdateOption(Option op);
    protected abstract void doDeleteOption(Option op);
    protected abstract List<Option> doGetAllOptions();
    protected abstract void doDeleteAllOptionsForTariff(long id);
    protected abstract long doSize();
    protected abstract List doGetAllOptionsForTariff(long id);

    public void createOption(Option op){
        doCreateOption(op);
    }

    public Option loadOption(long id) {
        return doLoadOption(id);
    }

    public Option findOptionByTitleAndTariffId(String title, long id) {
        return doFindOptionByTitleAndTariffId(title, id);
    }

    public void updateOption(Option op) {
        doUpdateOption(op);
    }

    public void deleteOption(Option op) {
        doDeleteOption(op);
    }

    public List<Option> getAllOptions() {
        return doGetAllOptions();
    }

    public List<Option> getAllOptionsForTariff(long id) {
        return doGetAllOptionsForTariff(id);
    }

    public void deleteAllOptionsForTariff(long id) {
        doDeleteAllOptionsForTariff(id);
    }

    public long size() {
        return doSize();
    }
}
