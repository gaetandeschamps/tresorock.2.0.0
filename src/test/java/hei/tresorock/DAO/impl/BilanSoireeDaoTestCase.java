package hei.tresorock.DAO.impl;

import hei.tresorock.DAO.BilanSoireeDao;
import hei.tresorock.DAO.DataBaseCommunication.BilanSoireeImpl;
import hei.tresorock.DAO.DataBaseCommunication.DataBaseProvider;
import org.junit.Before;

import java.sql.Connection;
import java.sql.Statement;

/**
 * TestCase du DAO BilanSoiree. ATTENTION ! Pour réaliser les tests, nous utilisons une base de données stockées en local,
 * elle est formé et exécutée strictement identiquement que la principale. De cette manière, les données en "dur" ne sont pas
 * impactées.
 */
public class BilanSoireeDaoTestCase {

    private BilanSoireeDao bilanDao = new BilanSoireeImpl();

    /**
     * Cette méthode permet de réinitialiser la base de données afin d'y entrer des données uniquement pour les tests
     * Des données sont entrées pour réaliser les tests
     * @throws Exception
     */
    @Before
    public void initDb() throws Exception {
        try (Connection connection = DataBaseProvider.getdataBase().getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DELETE FROM Soiree");
            stmt.executeUpdate("DELETE FROM Client");
            stmt.executeUpdate("DELETE FROM Participe");
            }
    }
}
