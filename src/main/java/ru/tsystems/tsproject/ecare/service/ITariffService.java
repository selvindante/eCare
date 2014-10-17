package ru.tsystems.tsproject.ecare.service;

import ru.tsystems.tsproject.ecare.entities.Tariff;

import java.util.List;

/**
 * Created by Selvin
 * on 15.10.2014.
 */
public interface ITariffService {

    public Tariff saveOrUpdateTariff(Tariff tr);

    public Tariff loadTariff(long id);

    public void deleteTariff(long id);

    public List<Tariff> getAllTariffs();

    public void deleteAllTariffs();

    public long getNumberOfTariffs();
}
