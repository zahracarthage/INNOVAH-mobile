/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Objects;

/**
 *
 * @author  BenHsounaMedNour
 */
public class events {
    private int id;
    private String nom;
    private String date;
    private String capacite;
    private String description;
    private String adresse;
    
    public events() {
    }

    @Override
    public String toString() {
        return "events{" + "id=" + id + ", nom=" + nom + ", date=" + date + ", capacite=" + capacite + ", description=" + description + ", adresse=" + adresse + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCapacite() {
        return capacite;
    }

    public void setCapacite(String capacite) {
        this.capacite = capacite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public events(int id, String nom, String date, String capacite, String description, String adresse) {
        this.id = id;
        this.nom = nom;
        this.date = date;
        this.capacite = capacite;
        this.description = description;
        this.adresse = adresse;
    }

    public events(String nom, String date, String capacite, String description, String adresse) {
        this.nom = nom;
        this.date = date;
        this.capacite = capacite;
        this.description = description;
        this.adresse = adresse;
    }
public events(int id){
    this.id=id;
}

    
    
}
