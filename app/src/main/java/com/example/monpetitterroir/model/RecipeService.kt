package com.example.monpetitterroir.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * List of API Routes with request parameters and return type
 */
interface RecipeService {
    /**
     * Liste des recettes par ingrédient
     */
    @GET("/recipes/findByIngredients")
    fun recipesList(
            @Query("apiKey") apiKey: String,
            @Query("ingredients") ingredients: String
    ): Call<List<Recipe>>

    /**
     * Récupération de la recette par l'identifiant
     */
    @GET("/recipes/{id}/information")
    fun recipeDetail(
            @Path("id") id: String,
            @Query("apiKey") apiKey: String
    ): Call<Recipe>

    /**
     * Liste des ingrédients pour une recette
     */
    @GET("/recipes/{id}/ingredientWidget.json")
    fun ingredientDetail(
            @Path("id") id: String,
            @Query("apiKey") apiKey: String
    ): Call<Ingredient>

    /**
     * Liste des instructions pour une recette
     */
    @GET("/recipes/{id}/analyzedInstructions")
    fun instructionDetail(
            @Path("id") id: String,
            @Query("apiKey") apiKey: String
    ): Call<Ingredient>
}
