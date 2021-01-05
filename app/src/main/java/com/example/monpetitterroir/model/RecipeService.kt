package com.example.monpetitterroir.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * List of API Routes with request parameters and return type
 */
interface RecipeService {

    @GET("/recipes/findByIngredients")
    fun recipesList(
            @Query("apiKey") apiKey: String,
            @Query("ingredients") ingredients: String
    ): Call<List<Recipe>>

    @GET("/recipes/{id}/information")
    fun recipeDetail(
            @Path("id") id: String,
            @Query("apiKey") apiKey: String
    ): Call<Recipe>

}
