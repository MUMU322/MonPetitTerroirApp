package com.example.monpetitterroir.model;

import retrofit2.http.Url;

/**
 * Ingredient of a recipe
 */
public class Ingredient {
    private final int id;

    private final String name;

    private final String image;

    public final int getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final String getImage() {
        return this.image;
    }

    /**
     * Create an
     * @param id Ingredient id on the api
     * @param name Ingredient name
     * @param image Ingredient image url
     */
    public Ingredient(int id, String name, @Url String image) {
        super();
        this.id = id;
        this.name = name;
        this.image = image;
    }
}
