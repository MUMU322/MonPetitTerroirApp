package com.example.monpetitterroir.model

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Class to create the service to call from the API
 * @see Retrofit
 * @see MoshiConverterFactory
 */
object ServicesBuilder {
    private val url = "https://api.spoonacular.com/"

    private val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    fun <T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }
}
