package com.example.monpetitterroir.model;

/**
 * Classe modélisant une recette de cuisine
 */
public class Recette {

    /**
     * le titre de la recette
     */
    String titre;

    /**
     * La description de la recette
     */
    String description;

    /**
     * Constructeur d'une recette
     * @param titre
     *          le titre de la recette
     * @param description
     *             la description de la recette
     */
    public Recette(String titre, String description) {
        this.titre = titre;
        this.description = description;
    }

    /**
     * getteur du titre de la recette
     * @return
     *      le titre de la recette
     */
    public String getTitre() {
        return titre;
    }

    /**
     *setteur du titre
     * @param titre
     *          le nouveau titre
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }

    /**
     * getteur de la description
     * @return
     *         la description de la recette
     */
    public String getDescription() {
        return description;
    }

    /**
     * setteur de la description
     * @param description
     *              la nouvelle description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
