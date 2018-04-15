package hei.tresorock.DAO.DataBaseCommunication;

import hei.tresorock.DAO.BilanSoireeDao;
import hei.tresorock.entities.BilanSoiree;

import java.sql.*;
import java.time.LocalDate;

public class BilanSoireeImpl implements BilanSoireeDao{


    @Override
    public BilanSoiree getBilanSoiree(int idSoiree) {

        BilanSoiree bilanSoiree = new BilanSoiree(idSoiree, LocalDate.ofEpochDay(0), -1.0, -1.0,
                -1, -1, "Erreur de récupération du bilan de la soirée");

        if(idSoiree==-1){
            return bilanSoiree;
        }

        //récupération de la recette et du nombre de clients venus
        String query = "SELECT sum(PrixPaye) AS RecetteCaisse, count(IdClient) AS NombreClients FROM Participe WHERE IdSoiree=?;";
        try (Connection connection = DataBaseProvider.getdataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idSoiree);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    bilanSoiree.setRecette(resultSet.getDouble("RecetteCaisse"));
                    bilanSoiree.setNbClients(resultSet.getInt("NombreClients"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //récupération du nombre de clients "abonnés" venus
        String query2 = "Select count(participe.IdClient) AS NombreClients FROM participe INNER JOIN client ON participe.IdClient=client.IdClient WHERE IdSoiree=? AND Statut=\"Abonné\";";
        try (Connection connection = DataBaseProvider.getdataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(query2)) {
            statement.setInt(1, idSoiree);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    bilanSoiree.setNbClientsAbos(resultSet.getInt("NombreClients"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        //récupération de la date, de l'erreur de caisse, et du thème de la soirée
        String query3 = "Select DateSoiree, ErreurDeCaisse, Theme FROM Soiree WHERE IdSoiree=?;";
        try (Connection connection = DataBaseProvider.getdataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(query3)) {
            statement.setInt(1, idSoiree);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    bilanSoiree.setDateSoiree(resultSet.getDate("DateSoiree").toLocalDate());
                    bilanSoiree.setErreurCaisse(resultSet.getDouble("ErreurDeCaisse"));
                    bilanSoiree.setTheme(resultSet.getString("Theme"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return bilanSoiree;
    }

    @Override
    public BilanSoiree updateActif() {
        String query = "UPDATE Soiree SET Actif=0 WHERE Actif=1;";
        try (Connection connection = DataBaseProvider.getdataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)){
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }


}
