package ru.tsystems.tsproject.ecare.storage;

import ru.tsystems.tsproject.ecare.entities.Option;

import java.util.List;

/**
 * Created by Selvin
 * on 06.10.2014.
 */
public abstract class AbstractOptionDAO {
    protected abstract void doCreateOption(Option op);
    protected abstract Option doLoadOption(long id);
    protected abstract void doUpdateOption(Option op);
    protected abstract void doDeleteOption(long id);
    protected abstract List<Option> doGetAll();
    protected abstract void doDeleteAll();
    protected abstract long doSize();

    public void createOption(Option op){
        doCreateOption(op);
    }

    public Option loadOption(long id) {
        return doLoadOption(id);
    }

    public void updateOption(Option op) {
        doUpdateOption(op);
    }

    public void deleteOption(long id) {
        doDeleteOption(id);
    }

    public List<Option> getAll() {
        return doGetAll();
    }

    public void deleteAll() {
        doDeleteAll();
    }

    public long size() {
        return doSize();
    }
}
