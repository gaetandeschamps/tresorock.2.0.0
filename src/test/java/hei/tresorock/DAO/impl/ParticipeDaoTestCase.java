package hei.tresorock.DAO.impl;

import hei.tresorock.DAO.DataBaseCommunication.DataBaseProvider;
import hei.tresorock.DAO.DataBaseCommunication.ParticipeDaoImpl;
import hei.tresorock.DAO.ParticipeDao;
import hei.tresorock.entities.Participe;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

/**
 * TestCase du DAO Participe. ATTENTION ! Pour réaliser les tests, nous utilisons une base de données stockées en local,
 * elle est formé et exécutée strictement identiquement que la principale. De cette manière, les données en "dur" ne sont pas
 * impactées.
 */
public class ParticipeDaoTestCase {

    private ParticipeDao participeDao = new ParticipeDaoImpl();

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
            stmt.executeUpdate("INSERT INTO `Participe`(`IdSoiree`,`IdClient`,`PrixPaye`) VALUES (1,2,1.50)");
            stmt.executeUpdate("INSERT INTO `Participe`(`IdSoiree`,`IdClient`,`PrixPaye`) VALUES (1,1,2.00)");
            stmt.executeUpdate("INSERT INTO `Participe`(`IdSoiree`,`IdClient`,`PrixPaye`) VALUES (3,4,15.00)");
            stmt.executeUpdate("INSERT INTO `Participe`(`IdSoiree`,`IdClient`,`PrixPaye`) VALUES (5,6,20.00)");
            stmt.executeUpdate("INSERT INTO `Soiree`(`IdSoiree`,`DateSoiree`,`RecetteDeCaisse`,`ErreurDeCaisse`,`Theme`,`Actif`) VALUES (1,'2018-01-01',100,5,'Test Case',TRUE )");

        }
    }
    /**
     * Cette méthode permet de tester la méthode listParticipe()
     * listParticipe() permet de lister les participants présents dans la base données
     */
    @Test
    public void shouldListParticipe() {
        // WHEN
        List<Participe> participes = participeDao.listParticipe();
        // THEN
        assertThat(participes).hasSize(4);
        assertThat(participes).extracting("idSoiree", "idClient", "prixPaye").containsOnly(
                tuple(1, 2, 1.50),
                tuple(1, 1, 2.00),
                tuple(3, 4, 15.00),
                tuple(5, 6, 20.00)
                );
    }
    /**
     * Cette méthode permet de tester la méthode getParticipe()
     * getParticipe() permet de récupérer seulement un participant en particulier de la base de données
     */
    @Test
    public void shouldGetParticipe() {
        // WHEN
        Participe participe = participeDao.getParticipe(1,1);
        // THEN
        assertThat(participe).isNotNull();
        assertThat(participe.getIdSoiree()).isEqualTo(1);
        assertThat(participe.getIdClient()).isEqualTo(1);
        assertThat(participe.getPrixPaye()).isEqualTo(2.00);
    }
    /**
     * Cette méthode permet de tester la méthode d'ajout de client addParticipe()
     * addParticipe() permet d'ajouter un participant dans la base de donnée, à partir de la création d'une soirée et de l'insertion d'un clien
     * @throws Exception
     */
    @Test
    public void shouldAddParticipe() throws Exception {
        // GIVEN
        Participe newParticipe = new Participe(8, 9, 20.00);
        // WHEN
        Participe createdParticipe = participeDao.addParticipe(newParticipe);

        try (Connection connection = DataBaseProvider.getdataBase().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM Participe WHERE IdSoiree='3'")) {
                assertThat(rs.next()).isTrue();
                assertThat(rs.getInt("IdSoiree")).isEqualTo(3);
                assertThat(rs.getInt("IdClient")).isEqualTo(4);
                assertThat(rs.getDouble("PrixPaye")).isEqualTo(15.00);
                assertThat(rs.next()).isFalse();
            }
        }
    }

    /**
     * Cette méthode permet de tester la méthode countRecetteTotale()
     * countRecetteTotale() permet de calculer la recetteTotale gagnée tout au long d'une soirée
     * elle est établie par rapport à une commande SQL qui effectue la somme de tous les prix payés
     * @throws Exception
     */
    @Test
    public void shouldCountRecetteTotale() throws Exception {

        try (Connection connection = DataBaseProvider.getdataBase().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT SUM(PrixPaye) AS somme FROM Participe WHERE IdSoiree=1")) {
                assertThat(rs.next()).isTrue();
                assertThat(rs.getDouble("somme")).isEqualTo(3.50);

            }
        }
    }

}
