package ru.tsystems.tsproject.ecare.service;

import ru.tsystems.tsproject.ecare.entities.Client;

import java.util.List;

/**
 * Created by Selvin
 * on 15.10.2014.
 */
public interface IClientService {

    public void createClient(Client cl);

    public Client loadClient(long id);

    public Client findClient(String login, String password);

    public Client findClientByNumber(long number);

    public void deleteClient(long id);

    public List<Client> getAllClients();

    public void deleteAllClients();

    public long getNumberOfClients();
}
