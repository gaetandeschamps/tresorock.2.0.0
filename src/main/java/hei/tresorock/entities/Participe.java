package hei.tresorock.entities;

/**
 * Classe définissant les différents paramètres/caractéristiques d'un participant à une soirée.
 * @author gaetandeschamps, clementgeorjon
 */
public class Participe {

    private Integer idSoiree;
    private Integer idClient;
    private Double prixPaye;

    /**
     * Méthode permettant d'accéder aux infos d'un participant.
     * @param idSoiree - identifiant unique d'une soirée
     * @param idClient - identifiant unique d'un client
     * @param prixPaye - prix payé par le client/participant
     */
    public Participe(int idSoiree, int idClient, double prixPaye) {
        this.idSoiree = idSoiree;
        this.idClient = idClient;
        this.prixPaye = prixPaye;
    }

    /**
     * Méthode récupérant l'identifiant unique d'une soirée.
     * @return - identifiant unique soirée
     */
    public int getIdSoiree() {
        return idSoiree;
    }

    /**
     * Méthode permettant de définir l'identifiant d'une soirée.
     * @param idSoiree - identifiant unique d'une soirée
     */
    public void setIdSoiree(int idSoiree) {
        this.idSoiree = idSoiree;
    }

    /**
     * Méthode permettant de récupérer l'identifiant unique d'un client.
     * @return idClient - identifiant unqiue client.
     */
    public int getIdClient() {
        return idClient;
    }

    /**
     * Méthode permettant de définir l'identifiant d'un client.
     * @param IdClient - identifiant unique client.
     */
    public void setIdClient(int IdClient) {
        this.idClient = idClient;
    }

    /**
     * Méthode permettant de récupérer le prix payé par le client.
     * @return prixPaye - prix payé par le client
     */
    public double getPrixPaye() {
        return prixPaye;
    }

    /**
     * Méthode permettant de définir le prix payé par le client.
     * @param prixPaye - prix payé par le client
     */
    public void setPrixPaye(double prixPaye) {
        this.prixPaye = prixPaye;
    }
}
