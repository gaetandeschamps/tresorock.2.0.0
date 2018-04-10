package hei.tresorock.entities;

/**
 * Classe définissant les différents paramètres/caractéristiques d'un client participant à une soirée.
 * @author gaetandeschamps, clementgeorjon
 */
public class Client {

    private Integer idClient;
    private String nomClient;
    private String prenomClient;
    private String ecoleClient;
    private boolean cotisantClient;
    private String statutClient;

    /**
     * Méthode permettant d'accéder aux infos d'un participant.
     *
     * @param idClient       - identifiant unique du client
     * @param nomClient      - nom du client
     * @param prenomClient   - prenom du client
     * @param ecoleClient    - ecole du client
     * @param cotisantClient - le client est-il cotisant ou non (boolean)
     * @param statutClient   - satut du client (Normal ou Abonné)
     */
    public Client(Integer idClient, String nomClient, String prenomClient, String ecoleClient, boolean cotisantClient, String statutClient) {
        super();
        this.idClient = idClient;
        this.nomClient = nomClient;
        this.prenomClient = prenomClient;
        this.ecoleClient = ecoleClient;
        this.cotisantClient = cotisantClient;
        this.statutClient = statutClient;
    }

    /**
     * Méthode récupérant l'id d'un client.
     * @return - identifiant unique d'un client.
     */
    public Integer getIdClient() {
        return idClient;
    }

    /**
     * Méthode permettant de définir l'id d'un client
     * @param idClient - identifiant unique d'un client.
     */
    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    /**
     * Méthode permettant de récupérer le nom d'un ou de plusieurs clients
     * @return - nom du ou des clients
     */
    public String getNomClient() {
        return nomClient;
    }

    /**
     * Méthode permettant de définir le nom d'un client
     * @param nomClient - nom du client
     */
    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    /**
     * Méthode permettant de récupérer le prénom d'un client
     * @return - prenom d'un client
     */
    public String getPrenomClient() {
        return prenomClient;
    }

    /**
     * Méthode permettant de définir le prénom d'un client
     * @param prenomClient - nom d'un client
     */
    public void setPrenomClient(String prenomClient) {
        this.prenomClient = prenomClient;
    }

    /**
     * Méthode permettant de récupérer l'école que le client fréquente
     * @return - école du client
     */
    public String getEcoleClient() {
        return ecoleClient;
    }

    /**
     * Méthode permettant de définir l'école fréquentée par le client
     * @param ecoleClient - école du client
     */
    public void setEcoleClient(String ecoleClient) {
        this.ecoleClient = ecoleClient;
    }

    /**
     * Méthode permettant de définir si un client est cotisant
     * @return - boolean indiquant que le client est cotisant
     */
    public boolean isCotisantClient() {
        return cotisantClient;
    }

    /**
     * Méthode permettant de définir si le client est cotisant
     * @param cotisantClient - client cotisant
     */
    public void setCotisantClient(boolean cotisantClient) {
        this.cotisantClient = cotisantClient;
    }

    /**
     * Méthode permettant de récupérer le type de client (normal ou abonné)
     * @return - le statut du client (normal ou abonné)
     */
    public String getStatutClient() {
        return statutClient ;
    }

    /**
     * Méthode permettant de définir type de client (normal ou abonné)
     * @param statutClient - normal ou abonné
     */
    public void setStatutClient(String statutClient) {
        this.statutClient = statutClient;
    }
}



