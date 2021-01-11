package com.example.monpetitterroir.model;

import java.util.ArrayList;
import java.util.List;

public class ListIngredients {


    private List<Ingredient> ingredients;


    public ListIngredients(List<Ingredient> listeIngredients) {
        this.ingredients = listeIngredients;
    }

    public List<Ingredient> getListeIngredients() {
        return ingredients;
    }
}
