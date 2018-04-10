package hei.tresorock.entities;

import java.time.LocalDate;

public class BilanSoiree {

    private Integer idSoiree;
    private LocalDate dateSoiree;
    private Double recette;
    private Double erreurCaisse;
    private Integer nbClients;
    private Integer nbClientsAbos;
    private String theme;

    public BilanSoiree(Integer idSoiree, LocalDate dateSoiree, Double recette, Double erreurCaisse, Integer nbClients, Integer nbClientsAbos, String theme) {
        this.idSoiree = idSoiree;
        this.dateSoiree = dateSoiree;
        this.recette = recette;
        this.erreurCaisse = erreurCaisse;
        this.nbClients = nbClients;
        this.nbClientsAbos = nbClientsAbos;
        this.theme = theme;
    }

    public Integer getIdSoiree() {
        return idSoiree;
    }

    public void setIdSoiree(Integer idSoiree) {
        this.idSoiree = idSoiree;
    }

    public LocalDate getDateSoiree() {
        return dateSoiree;
    }

    public void setDateSoiree(LocalDate dateSoiree) {
        this.dateSoiree = dateSoiree;
    }

    public Double getRecette() {
        return recette;
    }

    public void setRecette(Double recette) {
        this.recette = recette;
    }

    public Double getErreurCaisse() {
        return erreurCaisse;
    }

    public void setErreurCaisse(Double erreurCaisse) {
        this.erreurCaisse = erreurCaisse;
    }

    public Integer getNbClients() {
        return nbClients;
    }

    public void setNbClients(Integer nbClients) {
        this.nbClients = nbClients;
    }

    public Integer getNbClientsAbos() {
        return nbClientsAbos;
    }

    public void setNbClientsAbos(Integer nbClientsAbos) {
        this.nbClientsAbos = nbClientsAbos;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
