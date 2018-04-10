package hei.tresorock.DAO.DataBaseCommunication;

import hei.tresorock.DAO.ClientDao;
import hei.tresorock.entities.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe permet de faire le lien avec la table Client dans la BDD
 */
public class ClientDaoImpl implements ClientDao{

    /**
     * Cette méthode retourne une liste de tous les clients présents dans la BDD dans une structure List
     * @return une List<> d'objets Client
     */
    @Override
    public List<Client> listClient() {
        String query = "SELECT * FROM Client ORDER BY Nom";
        List<Client> listOfClients = new ArrayList<>();
        try (
                Connection connection = DataBaseProvider.getdataBase().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                listOfClients.add(
                        new Client(
                                resultSet.getInt("idClient"),
                                resultSet.getString("Nom"),
                                resultSet.getString("Prenom"),
                                resultSet.getString("Ecole"),
                                resultSet.getBoolean("Cotisant"),
                                resultSet.getString("Statut")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfClients;
    }

    /**
     * Cette méthode retourne un objet Client correpsondant à l'IdClient passé en paramètre
     * @param idClient du client recherché
     * @return null
     */
    @Override
    public Client getClient(Integer idClient) {
        String query = "SELECT * FROM Client WHERE idClient=?";
        try (Connection connection = DataBaseProvider.getdataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idClient);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Client(
                            resultSet.getInt("idClient"),
                            resultSet.getString("Nom"),
                            resultSet.getString("Prenom"),
                            resultSet.getString("Ecole"),
                            resultSet.getBoolean("Cotisant"),
                            resultSet.getString("Statut"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Cette méthode permet de récupérer l'ID d'un client en se basant sur son nom, prénom, et école
     * @param client - dont on cherche l'ID
     * @return l'IdClient du client passé en paramètre, -1 si erreur
     */
    public int getClientId (Client client){
        int idClient=-1;
        String query = "SELECT IdClient FROM Client WHERE Nom=? AND Prenom=? AND Ecole=?;";
        try (Connection connection = DataBaseProvider.getdataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, client.getNomClient());
            statement.setString(2, client.getPrenomClient());
            statement.setString(3, client.getEcoleClient());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    idClient = resultSet.getInt("IdClient");
                    return idClient;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idClient;
    }

    /**
     * Cette méthode permet d'ajouter un client dans la base de données
     * @param client - client à ajouter
     * @return null
     */
    @Override
    public Client addClient(Client client) {
            String query = "INSERT INTO Client(Nom, Prenom, Ecole, Cotisant, Statut) VALUES(?, ?, ?, ?, ?)";
        try (Connection connection = DataBaseProvider.getdataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, client.getNomClient());
            statement.setString(2, client.getPrenomClient());
            statement.setString(3, client.getEcoleClient());
            statement.setBoolean(4, client.isCotisantClient());
            statement.setString(5, client.getStatutClient());
            statement.executeUpdate();

            try (ResultSet ids = statement.getGeneratedKeys()) {
                if(ids.next()) {
                    int generatedId = ids.getInt(1);
                    client.setIdClient(generatedId);
                    return client;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
