package com.uvg.lab6pokemon.network

import retrofit2.http.GET
import retrofit2.http.Query

// Definir la interfaz de PokeAPI retrofit
interface PokeApiService {
    @GET("pokemon?limit=100&offset=0")
    suspend fun getPokemonList(@Query("limit") limit: Int): PokeResponse
}


