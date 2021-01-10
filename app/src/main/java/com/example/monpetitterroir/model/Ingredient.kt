package com.example.monpetitterroir.model

import retrofit2.http.Url

/**
 * Ingredient of a recipe
 * @param id Ingredient id on the api
 * @param name Ingredient name
 * @param image Ingredient image url
 */
class Ingredient (
        val id: Int,
        val name: String,
        @Url val image: String
) {
}