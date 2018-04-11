package hei.tresorock.managers;

import hei.tresorock.DAO.BilanSoireeDao;
import hei.tresorock.DAO.ClientDao;
import hei.tresorock.DAO.DataBaseCommunication.BilanSoireeImpl;
import hei.tresorock.DAO.DataBaseCommunication.ClientDaoImpl;
import hei.tresorock.DAO.DataBaseCommunication.ParticipeDaoImpl;
import hei.tresorock.DAO.DataBaseCommunication.SoireeDaoImpl;
import hei.tresorock.DAO.ParticipeDao;
import hei.tresorock.DAO.SoireeDao;
import hei.tresorock.entities.BilanSoiree;
import hei.tresorock.entities.Client;
import hei.tresorock.entities.Participe;
import hei.tresorock.entities.Soiree;

import java.util.List;

public class ListeSoiree {

    private static class ListeSoireeHolder {
        private final static ListeSoiree instance = new ListeSoiree();
    }

    public static ListeSoiree getInstance() {
        return ListeSoireeHolder.instance;
    }

    private ClientDao clientDao = new ClientDaoImpl();
    private SoireeDao soireeDao = new SoireeDaoImpl();
    private ParticipeDao participeDao = new ParticipeDaoImpl();
    private BilanSoireeDao bilanSoireeDao = new BilanSoireeImpl();

    private ListeSoiree() {
    }

    //méthode liées au traitement des informations des soirées
    public List<Soiree> listSoiree() {  return soireeDao.listSoiree();  }

    public Soiree getSoiree(Integer idSoiree){  return soireeDao.getSoiree(idSoiree);}

    public Soiree addSoiree (Soiree soiree){
        if (soiree == null) {
            throw new IllegalArgumentException("The soiree should not be null.");
        }
        if (soiree.getDateSoiree() == null) {
            throw new IllegalArgumentException("The date of the soiree should not be null.");
        }
        if (soiree.getRecetteCaisse() == null) {
            throw new IllegalArgumentException("The recetteCaisse of soiree should not be null.");
        }
        if (soiree.getThemeSoiree() == null) {
            throw new IllegalArgumentException("The soiree's Theme should not be null.");
        }
        if (soiree.getErreurCaisse() == null) {
            throw new IllegalArgumentException("The soiree's erreurCaisse should not be null.");
        }
        soireeDao.addSoiree(soiree);
        return soiree;
    }

    public int getSoireeEnCoursId () {
        return soireeDao.getSoireeEnCoursId();
    }

    //méthodes liées au traitement des informations des clients
    public List<Client> listClient() {
        return clientDao.listClient();
    }

    //récupère un client en connaissant son ID
    public Client getClient(Integer idClient) {
        return clientDao.getClient(idClient);
    }

    //récupère l'id d'un client en se basant sur son nom, son prenom et son école
    public int getClientId (Client client){
        return clientDao.getClientId(client);
    }

    public Client addClient(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("The client should not be null.");
        }
        if (client.getNomClient() == null || "".equals(client.getNomClient())) {
            throw new IllegalArgumentException("The client's name should not be empty.");
        }
        if (client.getPrenomClient() == null || "".equals(client.getPrenomClient())) {
            throw new IllegalArgumentException("The client's first name should not be empty.");
        }
        if (client.getEcoleClient()== null || "".equals(client.getEcoleClient())) {
            throw new IllegalArgumentException("The client's school should not be empty.");
        }
        if (client.getStatutClient() == null || "".equals(client.getStatutClient())) {
            throw new IllegalArgumentException("The client's status should not be null or equal to 0.");
        }

        clientDao.addClient(client);
        return client;
    }

    //méthodes liées au traitement des informations des participations des clients aux soirées
    public List<Participe> listParticipe() {    return participeDao.listParticipe();    }

    public Participe getParticipeDao(Integer idSoiree,Integer idClient) { return participeDao.getParticipe(idSoiree,idClient);   }

    public Participe addParticipe(Participe participe) {
        if (participe == null) {
            throw new IllegalArgumentException("Participe should not be null.");
        }
        if (participe.getPrixPaye() == -1){
            throw new IllegalArgumentException("Le prix payé ne doit pas être nul");
        }

        participeDao.addParticipe(participe);
        return participe;
    }

    public Double countRecetteTotale(Integer idSoiree){
        return participeDao.countRecetteTotale(idSoiree);

    }

    public BilanSoiree getBilanSoiree(int idSoiree){
        return bilanSoireeDao.getBilanSoiree(idSoiree);
    }


}
