package com.uvg.lab6pokemon.network

import com.google.gson.annotations.SerializedName

data class PokeResponse(val results: List<Pokemon>)

// Modelo para el Pokémon (simplificado) (modelos de datos)
data class Pokemon(
    val id: Int,
    val name: String,
    val url: String,
    @SerializedName("front_default") val frontDefault: String
) {
    val ImageUrlFront: String get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
    val ImageUrlBack: String get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/$id.png"
    val ImageUrlShinyFront: String get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/$id.png"
    val ImageUrlShinyBack: String get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/shiny/$id.png"
}
