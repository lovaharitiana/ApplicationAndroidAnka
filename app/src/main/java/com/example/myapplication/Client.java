package com.example.myapplication;

public class Client {
    private String nom;
    private String categorie;
    private String code;
    private String date;
    private String numero;
    private String village;
    private String modePayment;
    private String activite;

    public Client(String nom, String categorie, String code, String date, String numero, String village, String modePayment, String activite) {
        this.nom = nom;
        this.categorie = categorie;
        this.code = code;
        this.date = date;
        this.numero = numero;
        this.village = village;
        this.modePayment = modePayment;
        this.activite = activite;
    }

    // Getters
    public String getNom() { return nom; }
    public String getCategorie() { return categorie; }
    public String getCode() { return code; }
    public String getDate() { return date; }
    public String getNumero() { return numero; }
    public String getVillage() { return village; }
    public String getModePayment() { return modePayment; }
    public String getActivite() { return activite; }

    public void setNom(String toString) {
    }

    public void setCategorie(String toString) {
    }

    public void setCode(String toString) {
    }

    public void setDate(String toString) {
    }

    public void setNumero(String toString) {
    }

    public void setVillage(String toString) {
    }

    public void setModePayment(String toString) {
    }

    public void setActivite(String toString) {
    }
}
