package ru.tsystems.tsproject.ecare.service;

import ru.tsystems.tsproject.ecare.entities.Option;

import java.util.List;

/**
 * Created by Selvin
 * on 15.10.2014.
 */
public interface IOptionService {

    public Option saveOrUpdateOption(Option op);

    public Option loadOption(long id);

    public Option findOptionByTitleAndTariffId(String title, long id);

    public void deleteOption(long id);

    public List<Option> getAllOptions();

    public List<Option> getAllOptionsForTariff(long id);

    public void deleteAllOptionsForTariff(long id);

    public long getNumberOfOptions();

    public void setDependentOption(Option currentOption, Option dependentOption);

    public void deleteDependentOption(Option currentOption, Option dependentOption);

    public void setIncompatibleOption(Option currentOption, Option incompatibleOption);

    public void deleteIncompatibleOption(Option currentOption, Option incompatibleOption);
}
