/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author Zannou
 */
public class Activites {
    private int idact;
    private String nom;
    private String description;
    private String categorie;
    private String date;
    private String image;
    private int prix;

    public Activites() {
    }

    public Activites(String nom, String description, String categorie, String date, String image, int prix) {
        this.nom = nom;
        this.description = description;
        this.categorie = categorie;
        this.date = date;
        this.image = image;
        this.prix = prix;
    }

    public Activites(int idact, String nom, String description, String categorie, String date, String image, int prix) {
        this.idact = idact;
        this.nom = nom;
        this.description = description;
        this.categorie = categorie;
        this.date = date;
        this.image = image;
        this.prix = prix;
    }

    public int getIdact() {
        return idact;
    }

    public void setIdact(int idact) {
        this.idact = idact;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Activites{" + "idact=" + idact + ", nom=" + nom + ", description=" + description + ", categorie=" + categorie + ", date=" + date + ", image=" + image + ", prix=" + prix + '}';
    }
    
    
}
