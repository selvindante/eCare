package ru.tsystems.tsproject.ecare;

import ru.tsystems.tsproject.ecare.dao.AbstractDAO;
import ru.tsystems.tsproject.ecare.dao.SqlClientDAO;
import ru.tsystems.tsproject.ecare.dao.SqlEntityManager;
import ru.tsystems.tsproject.ecare.entities.Client;

import javax.persistence.EntityManager;

/**
 * Created by Selvin
 * on 04.10.2014.
 */
public class Main {
    public static void main(String[] args) {
        EntityManager em = SqlEntityManager.getEm();
        AbstractDAO<Client> clDAO = new SqlClientDAO(em);
    }
}
