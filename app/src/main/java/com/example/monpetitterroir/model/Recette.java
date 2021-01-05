package com.example.monpetitterroir.model;

public class Recette {

    /**
     * le titre de la recette
     */
    String titre;

    /**
     * La description de la recette
     */
    String description;

    public Recette(String titre, String description) {
        this.titre = titre;
        this.description = description;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}