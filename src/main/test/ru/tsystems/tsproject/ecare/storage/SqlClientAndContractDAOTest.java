package ru.tsystems.tsproject.ecare.storage;

/**
 * Created by Selvin
 * on 07.10.2014.
 */
public class SqlClientAndContractDAOTest extends ClientAndContractDAOTest {
    static {
        clst = new SqlClientDAO();
        cnst = new SqlContractDAO();
        trst = new SqlTariffDAO();
        opst = new SqlOptionDAO();
    }
}
