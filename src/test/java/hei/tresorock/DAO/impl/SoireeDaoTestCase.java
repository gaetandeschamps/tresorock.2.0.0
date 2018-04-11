package hei.tresorock.DAO.impl;

import hei.tresorock.DAO.DataBaseCommunication.DataBaseProvider;
import hei.tresorock.DAO.DataBaseCommunication.SoireeDaoImpl;
import hei.tresorock.DAO.SoireeDao;
import hei.tresorock.entities.Soiree;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

/**
 * TestCase du DAO Soiree. ATTENTION ! Pour réaliser les tests, nous utilisons une base de données stockées en local,
 * elle est formé et exécutée strictement identiquement que la principale. De cette manière, les données en "dur" ne sont pas
 * impactées.
 */
public class SoireeDaoTestCase {
    private SoireeDao soireeDao = new SoireeDaoImpl();

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
            stmt.executeUpdate("INSERT INTO `Soiree`(`IdSoiree`,`DateSoiree`,`RecetteDeCaisse`,`ErreurDeCaisse`,`Theme`,`Actif`) VALUES (1,'2018-01-01',100,5,'Test Case',TRUE )");
            stmt.executeUpdate("INSERT INTO `Soiree`(`IdSoiree`,`DateSoiree`,`RecetteDeCaisse`,`ErreurDeCaisse`,`Theme`,`Actif`) VALUES (2,'2018-02-14',86,17,'Test Case 2',FALSE )");
        }
    }

    /**
     * Cette méthode permet de tester la méthode listClient()
     * listClient() permet de lister les clients présents dans la base données
     */
    @Test
    public void shouldListSoiree() {
        // WHEN
        List<Soiree> soirees = soireeDao.listSoiree();
        // THEN
        assertThat(soirees).hasSize(2);
        assertThat(soirees).extracting("idSoiree", "dateSoiree", "recetteCaisse", "erreurCaisse", "themeSoiree", "actif").containsOnly(
                tuple(1, LocalDate.of(2018, 01, 01), 100.0, 5.0, "Test Case", true),
                tuple(2, LocalDate.of(2018, 02, 14), 86.0, 17.0, "Test Case 2", false)
                );
    }
    /**
     * Cette méthode permet de tester la méthode getSoiree()
     * getSoiree() permet de récupérer seulement une soirée en particulier de la base de données
     */
    @Test
    public void shouldGetSoiree() {
        // WHEN
        Soiree soiree = soireeDao.getSoiree(2);
        // THEN
        assertThat(soiree).isNotNull();
        assertThat(soiree.getIdSoiree()).isEqualTo(2);
        assertThat(soiree.getDateSoiree()).isEqualTo(LocalDate.of(2018,02,14));
        assertThat(soiree.getRecetteCaisse()).isEqualTo(86.0);
        assertThat(soiree.getErreurCaisse()).isEqualTo(17.0);
        assertThat(soiree.getThemeSoiree()).isEqualTo("Test Case 2");
        assertThat(soiree.getActif()).isFalse();
    }
    /**
     * Cette méthode permet de tester la méthode d'ajout de client addSoiree()
     * addSoiree() permet d'ajouter une soiree dans la base de donnée, moyennant le formulaire d'entrée
     * @throws Exception
     */
    @Test
    public void shouldAddSoiree() throws Exception {
        // GIVEN
        Soiree newSoiree = new Soiree(null, LocalDate.of(2018,10,19),
                237.50, 8.90, "Nouveau Thème", Boolean.FALSE);
        // WHEN
        Soiree createdSoiree = soireeDao.addSoiree(newSoiree);
        // THEN
        assertThat(createdSoiree).isNotNull();
        assertThat(createdSoiree.getIdSoiree()).isNotNull();
        assertThat(createdSoiree.getIdSoiree()).isGreaterThan(0);
        assertThat(createdSoiree.getDateSoiree()).isEqualTo(LocalDate.of(2018,10,19));
        assertThat(createdSoiree.getRecetteCaisse()).isEqualTo(237.50);
        assertThat(createdSoiree.getErreurCaisse()).isEqualTo(8.90);
        assertThat(createdSoiree.getThemeSoiree()).isEqualTo("Nouveau Thème");
        assertThat(createdSoiree.getActif()).isEqualTo(Boolean.FALSE);


        try (Connection connection = DataBaseProvider.getdataBase().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM Soiree WHERE DateSoiree='2018-10-19'")) {
                assertThat(rs.next()).isTrue();
                assertThat(rs.getInt("IdSoiree")).isEqualTo(createdSoiree.getIdSoiree());
                assertThat(rs.getDate("DateSoiree").toLocalDate()).isEqualTo(LocalDate.of(2018, 10,19));
                assertThat(rs.getDouble("RecetteDeCaisse")).isEqualTo(237.50);
                assertThat(rs.getDouble("ErreurDeCaisse")).isEqualTo(8.90);
                assertThat(rs.getString("Theme")).isEqualTo("Nouveau Thème");
                assertThat(rs.getBoolean("Actif")).isFalse();

                assertThat(rs.next()).isFalse();
            }
        }
    }

    /**
     * Cette méthode permet de tester la méthode de récupération d'une soirée en cours getSoireeEnCoursId()
     * getSoireeEnCours() récupère une soirée dont l'attribut actif a été placé sur 1
     * @throws Exception
     */
    @Test
    public void shouldGetSoireeEnCoursId() throws Exception {
        //WHEN
        Soiree soiree = soireeDao.getSoiree(1);
        // THEN
        assertThat(soiree).isNotNull();
        assertThat(soiree.getIdSoiree()).isEqualTo(1);
        assertThat(soiree.getActif()).isEqualTo(Boolean.TRUE);
    }

}
