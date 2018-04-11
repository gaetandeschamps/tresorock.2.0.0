package hei.tresorock.DAO.impl;

import hei.tresorock.DAO.ClientDao;
import hei.tresorock.DAO.DataBaseCommunication.ClientDaoImpl;
import hei.tresorock.DAO.DataBaseCommunication.DataBaseProvider;
import hei.tresorock.entities.Client;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

/**
 * TestCase du DAO Client. ATTENTION ! Pour réaliser les tests, nous utilisons une base de données stockées en local,
 * elle est formé et exécutée strictement identiquement que la principale. De cette manière, les données en "dur" ne sont pas
 * impactées.
 */
public class ClientDaoTestCase {
    private ClientDao clientDao = new ClientDaoImpl();

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
            stmt.executeUpdate("INSERT INTO `Client`(`IdClient`,`Nom`,`Prenom`,`Ecole`,`Cotisant`,`Statut`) VALUES (1,'Deschamps','Gaëtan','HEI', TRUE,'Abonné')");
            stmt.executeUpdate("INSERT INTO `Client`(`IdClient`,`Nom`,`Prenom`,`Ecole`,`Cotisant`,`Statut`) VALUES (2,'Georjon','Clément','ISEN', FALSE,'Normal')");
        }
    }

    /**
     * Cette méthode permet de tester la méthode listClient()
     * listClient() permet de lister les clients présents dans la base données
     */
    @Test
    public void shouldListClient() {
        // WHEN
        List<Client> clients = clientDao.listClient();
        // THEN
        assertThat(clients).hasSize(2);
        assertThat(clients).extracting("idClient", "nomClient", "prenomClient", "ecoleClient", "cotisantClient", "statutClient").containsOnly(
                tuple(1, "Deschamps","Gaëtan","HEI",true,"Abonné"),
                tuple(2, "Georjon","Clément","ISEN",false,"Normal")
        );
    }

    /**
     * Cette méthode permet de tester la méthode getClient()
     * getClient() permet de récupérer seulement un client en particulier de la base de données
     */
    @Test
    public void shouldGetClient() {
        // WHEN
        Client client = clientDao.getClient(2);
        // THEN
        assertThat(client).isNotNull();
        assertThat(client.getIdClient()).isEqualTo(2);
        assertThat(client.getNomClient()).isEqualTo("Georjon");
        assertThat(client.getPrenomClient()).isEqualTo("Clément");
        assertThat(client.getEcoleClient()).isEqualTo("ISEN");
        assertThat(client.isCotisantClient()).isEqualTo(false);
        assertThat(client.getStatutClient()).isEqualTo("Normal");
    }

    /**
     * Cette méthode permet de tester la méthode d'ajout de client addClient()
     * addClient() permet d'ajouter un client dans la base de donnée, moyennant le formulaire d'entrée
     * @throws Exception
     */
    @Test
    public void shouldAddClient() throws Exception {
        // GIVEN
        Client newClient = new Client(null, "Skywalker","Luke","Star Wars",true,"Abonné");
        // WHEN
        Client createdClient = clientDao.addClient(newClient);
        // THEN
        assertThat(createdClient).isNotNull();
        assertThat(createdClient.getIdClient()).isNotNull();
        assertThat(createdClient.getIdClient()).isGreaterThan(0);
        assertThat(createdClient.getNomClient()).isEqualTo("Skywalker");
        assertThat(createdClient.getPrenomClient()).isEqualTo("Luke");
        assertThat(createdClient.getEcoleClient()).isEqualTo("Star Wars");
        assertThat(createdClient.isCotisantClient()).isEqualTo(true);
        assertThat(createdClient.getStatutClient()).isEqualTo("Abonné");

        try (Connection connection = DataBaseProvider.getdataBase().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM Client WHERE Nom='Skywalker'")) {
                assertThat(rs.next()).isTrue();
                assertThat(rs.getInt("IdClient")).isEqualTo(createdClient.getIdClient());
                assertThat(rs.getString("Nom")).isEqualTo("Skywalker");
                assertThat(rs.getString("Prenom")).isEqualTo("Luke");
                assertThat(rs.getString("Ecole")).isEqualTo("Star Wars");
                assertThat(rs.getBoolean("Cotisant")).isTrue();
                assertThat(rs.getString("Statut")).isEqualTo("Abonné");

                assertThat(rs.next()).isFalse();
            }
        }
    }

    @Test
    public void shouldGetClientId() {
        // WHEN
        Client client = clientDao.getClient(1);
        // THEN
        assertThat(client).isNotNull();
        assertThat(client.getIdClient()).isEqualTo(1);
        assertThat(client.getNomClient()).isEqualTo("Deschamps");
        assertThat(client.getPrenomClient()).isEqualTo("Gaëtan");
        assertThat(client.getEcoleClient()).isEqualTo("HEI");
    }
}
