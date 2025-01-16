package com.example.pokeapi.view.pokemonScreen.pokemonListScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.pokeapi.data.database.PokeData
import com.example.pokeapi.data.Resource
import com.example.pokeapi.view.pokemonScreen.PokeListViewModel

@Composable
fun PokeListView(pokeListViewModel: PokeListViewModel = hiltViewModel(), paddingValues: PaddingValues) {
    val searchQuery = remember { mutableStateOf("") }

    AllPokemonScreen(pokeListViewModel, searchQuery, paddingValues)
}

@Composable
fun AllPokemonScreen(viewModel: PokeListViewModel, searchQuery: MutableState<String>, paddingValues: PaddingValues) {

    val allPokemon by viewModel.allPokemon.collectAsState()

    when (allPokemon) {
        is Resource.Loading -> CircularProgressIndicator()
        is Resource.Success -> {
            val pokeList = (allPokemon as Resource.Success<List<PokeData>>).data


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                PokemonSearchScreen(searchQuery)
                LazyColumn() {
                    if (pokeList != null) {
                        items(pokeList.size) { poke ->
                            if (searchQuery.value.isNotEmpty() && searchQuery.value.length >= 3) {
                                val pokemon = pokeList[poke]

                                if (pokemon.name.contains(
                                        searchQuery.value,
                                        ignoreCase = true
                                    )
                                ) {
                                    RenderPokemon(pokemon, viewModel)
                                }


                            } else {
                                val pokemon = pokeList[poke]
                                RenderPokemon(pokemon, viewModel)
                            }
                        }
                    }
                }
            }

        }

        is Resource.Error -> {
            (allPokemon as Resource.Error).message?.let {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Red),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = it)
                }
            }
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonScreen(viewModel: PokeListViewModel, name: String, closeClick: () -> Unit) {
    viewModel.fetchPokemonDetail(name)
    viewModel.fetchPokemonImage(name)

    val pokemon = viewModel.pokemonDetail.value
    val pokemonImage = viewModel.pokemonImage.value


    Box() {
        BasicAlertDialog(onDismissRequest = {}) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
                    .padding(start = 15.dp, bottom = 15.dp, top = 15.dp)
            ) {
                Column() {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = name.uppercase())
                        IconButton(onClick = { closeClick() }
                        ) {
                            Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
                        }
                    }

                    pokemon?.let {
                        // Mostrar tipos
                        Text("Tipos: ${it.types.joinToString { type -> type.type.name }}")

                        // Mostrar habilidades
                        Text("Habilidades: ${it.abilities.joinToString { ability -> ability.ability.name }}")

                        Spacer(modifier = Modifier.height(8.dp))

                    }
                    Row(horizontalArrangement = Arrangement.SpaceBetween) {
                        pokemonImage?.let {

                            Image(
                                painter = rememberAsyncImagePainter(it.sprites.imageFront),
                                contentDescription = null,
                                modifier = Modifier.size(150.dp),
                            )
                            Image(
                                painter = rememberAsyncImagePainter(it.sprites.imageBack),
                                contentDescription = null,
                                modifier = Modifier.size(150.dp),
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RenderPokemon(pokeData: PokeData, viewModel: PokeListViewModel) {
    var showAlert by remember { mutableStateOf(false) }
    ListItem(
        {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { showAlert = true }) {
                Row {
                    Column {
                        Text(
                            text = pokeData.name.uppercase(),
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Normal
                        )
                    }
                }
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(5.dp)
                )
            }
        }
    )
    val name = pokeData.name
    if (showAlert) {
        PokemonScreen(
            viewModel = viewModel,
            name,
            closeClick = { showAlert = false }
        )
    }
}

@Composable
fun PokemonSearchScreen(searchQuery: MutableState<String>) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = searchQuery.value,
            onValueChange = { searchQuery.value = it },
            label = { Text("Enter Pokémon") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}
