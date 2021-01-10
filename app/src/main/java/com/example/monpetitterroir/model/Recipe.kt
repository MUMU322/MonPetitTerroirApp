package com.example.monpetitterroir.model

import androidx.annotation.NonNull
import androidx.annotation.StringRes
import retrofit2.http.Url

/**
 * A recipe with name, image, likes, ingredients
 * @param id recipe unique id from the API
 * @param image recipe image url
 * @param likes recipe number of likes
 * @param usedIngredients ingredients from the search, used in the recipe
 * @param missedIngredients list of ingredients not in the search, used in the recipe
 */
data class Recipe(
        var id: String,
        @Url var image: String,
        var title: String,
        var likes: Int,
        val usedIngredients: List<Ingredient>
) {
}
