package ru.tsystems.tsproject.ecare.storage;

import ru.tsystems.tsproject.ecare.entities.Tariff;

import java.util.List;

/**
 * Created by Selvin
 * on 06.10.2014.
 */
public abstract class AbstractTariffDAO {
    protected abstract void doCreateTariff(Tariff tr);
    protected abstract Tariff doLoadTariff(long id);
    protected abstract void doUpdateTariff(Tariff tr);
    protected abstract void doDeleteTariff(long id);
    protected abstract List<Tariff> doGetAll();
    protected abstract void doDeleteAll();
    protected abstract long doSize();

    public void createTariff(Tariff tr){
        doCreateTariff(tr);
    }

    public Tariff loadTariff(long id) {
        return doLoadTariff(id);
    }

    public void updateTariff(Tariff tr) {
        doUpdateTariff(tr);
    }

    public void deleteTariff(long id) {
        doDeleteTariff(id);
    }

    public List<Tariff> getAll() {
        return doGetAll();
    }

    public void deleteAll() {
        doDeleteAll();
    }

    public long size() {
        return doSize();
    }
}
