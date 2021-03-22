package com.example.monpetitterroir.model

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
 //       @StringRes val id: Int,
        @Url val image: String,
        val title: String,
  //      val likes: Int,
     //   val usedIngredients: List<Ingredient>,
    //    val missedIngredients : List<Ingredient>
) {
}
