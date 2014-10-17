package ru.tsystems.tsproject.ecare.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Selvin
 * on 07.10.2014.
 */
public class SqlEntityManager {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ECARE");
    private static EntityManager em = emf.createEntityManager();

    public static EntityManager getEm() {
        return em;
    }
}
