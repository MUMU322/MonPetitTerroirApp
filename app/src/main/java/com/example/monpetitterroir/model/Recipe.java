package com.example.monpetitterroir.model;

import java.util.List;

/**
 * Classe modélisant une recette de cuisine
 */
public class Recipe {
    String id;
    String image;
    String title;
    String likes;
    List<Ingredient> usedIngredients;

    public Recipe(String id, String image, String title, String likes, List<Ingredient> usedIngredients) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.likes = likes;
        this.usedIngredients = usedIngredients;
    }

    public String getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public List<Ingredient> getUsedIngredients() {
        return usedIngredients;
    }

    public void setUsedIngredients(List<Ingredient> usedIngredients) {
        this.usedIngredients = usedIngredients;
    }
}

