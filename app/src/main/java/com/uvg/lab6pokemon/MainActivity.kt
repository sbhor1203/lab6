package com.uvg.lab6pokemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.uvg.lab6pokemon.network.Pokemon
import com.uvg.lab6pokemon.network.Retrofitclass
import com.uvg.lab6pokemon.ui.theme.Lab6PokemonTheme
import kotlinx.coroutines.launch
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.ui.res.stringResource


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab6PokemonTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PokemonListScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun PokemonListScreen(pokemonList: List<Pokemon> = emptyList(), modifier: Modifier = Modifier) {
    val pokemonListState = remember { mutableStateOf(pokemonList) }
    val isLoading = remember { mutableStateOf(true) } // config para poder hacer el scroll
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        if (pokemonList.isEmpty()) {
            coroutineScope.launch {
                isLoading.value = true
                val response = Retrofitclass.apiService.getPokemonList(100)
                pokemonListState.value = response.results
                isLoading.value = false
            }
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).padding(16.dp)) {
            Text(stringResource(id = R.string.pokemon_list), style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))

            if (isLoading.value) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(stringResource(id = R.string.loading_text), style = MaterialTheme.typography.bodyLarge)
                }
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(pokemonListState.value) { pokemon ->
                        PokemonItem(pokemon)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}


@Composable
fun PokemonItem(pokemon: Pokemon) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
        shape = RoundedCornerShape(16.dp)) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),

        verticalAlignment = Alignment.CenterVertically
    ) {
        // mostrar imagen
        Image(
            painter = rememberAsyncImagePainter(pokemon.frontDefault),
            contentDescription = pokemon.name,
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(16.dp))


        Text(
            text = pokemon.name.capitalize(),
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black
        )
    }
}
}

/*
@Preview(showBackground = true)
@Composable
fun PokemonListScreenPreview() {
    val mockPokemonList = listOf(
        Pokemon(
            name = "Bulbasaur",
            url = "https://pokeapi.co/api/v2/pokemon/1/",
            id = 1,
            frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
        ),
        Pokemon(
            name = "Ivysaur",
            url = "https://pokeapi.co/api/v2/pokemon/2/",
            id = 2,
            frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png"
        ),
        Pokemon(
            name = "Venusaur",
            url = "https://pokeapi.co/api/v2/pokemon/3/",
            id = 3,
            frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/3.png"
        )
    )

    Lab6PokemonTheme {
        PokemonListScreen(pokemonList = mockPokemonList) // Pasa la lista simulada aquí
    }
}
*/

// falta llamar al endpoint de /pokemon para los nombres junto con el link y las imágenes y el viewmodel para mostrarlo
//falta las imagenes y la otra pantalla