/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Objects;

/**
 *
 * @author Azza
 */
public class Evenement {
      private int id;

    private String nom;
 
    

  
    private String Adresse;

  
    private String description;

   
    private String entreprise;

  
    private Double prix;
    
    private int cap;

    public Evenement() {
    }

    public Evenement(int id) {
        this.id = id;
    }

    public Evenement(int id, String nom, String Adresse, String description, String entreprise, Double prix, int cap) {
        this.id = id;
        this.nom = nom;
        this.Adresse = Adresse;
        this.description = description;
        this.entreprise = entreprise;
        this.prix = prix;
        this.cap = cap;
    }

    
    public int getId() {
        return id;
    }

    public Evenement(int id, String nom, String Adresse, String description, String entreprise, int cap) {
        this.id = id;
        this.nom = nom;
        this.Adresse = Adresse;
        this.description = description;
        this.entreprise = entreprise;
        this.cap = cap;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return Adresse;
    }

    public String getDescription() {
        return description;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public Double getPrix() {
        return prix;
    }

    public int getCap() {
        return cap;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdresse(String Adresse) {
        this.Adresse = Adresse;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public void setCap(int cap) {
        this.cap = cap;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", nom=" + nom + ", Adresse=" + Adresse + ", description=" + description + ", entreprise=" + entreprise + '}';
    }

   

   

    
    
}
