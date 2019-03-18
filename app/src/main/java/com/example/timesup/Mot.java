package com.example.timesup;

public class Mot {
    private int id;
    private String texte;
    private boolean filtre_bool;

    public Mot(String texte,boolean filtre_bool){
        this.texte=texte;
        this.filtre_bool=filtre_bool;
    }
    public Mot(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public boolean isFiltre_bool() {
        return filtre_bool;
    }

    public void setFiltre_bool(boolean filtre_bool) {
        this.filtre_bool = filtre_bool;
    }
}
