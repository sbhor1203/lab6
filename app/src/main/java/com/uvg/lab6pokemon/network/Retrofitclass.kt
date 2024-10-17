package com.uvg.lab6pokemon.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofitclass {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: PokeApiService = retrofit.create(PokeApiService::class.java)
}


// esto llama al cliente de retrofit

