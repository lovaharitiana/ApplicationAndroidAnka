package com.example.myapplication;

public class Compteur {
    private String numeroserie;
    private String datebranchement;

    public Compteur(String numeroserie, String datebranchement) {
        this.numeroserie = numeroserie;
        this.datebranchement = datebranchement;
    }

    public String getNumeroserie() {
        return numeroserie;
    }

    public String getDatebranchement() {
        return datebranchement;
    }

    public void setNumeroserie(String newNumeroserie) {
    }

    public void setDatebranchement(String newDatebranchement) {
    }
}
