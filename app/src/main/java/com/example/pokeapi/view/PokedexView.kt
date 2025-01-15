package com.example.pokeapi.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pokeapi.R
import com.example.pokeapi.data.response.PokeResponse
import com.example.pokeapi.viewModel.PokeListViewModel

@Composable
fun PokeListView(pokeListViewModel: PokeListViewModel = hiltViewModel()) {
    val searchQuery = remember { mutableStateOf("") }

    AllPokemonScreen(pokeListViewModel, searchQuery)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllPokemonScreen(viewModel: PokeListViewModel, searchQuery: MutableState<String>) {
    viewModel.fetchAllPokemon()
    val allPokemon = viewModel.allPokemon.value
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = { },
                title = {
                    Image(
                        painter = painterResource(id = R.drawable.pokeapi_256),
                        contentDescription = null,
                        //tint = Color.White
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    ) { padding ->

        Column (modifier = Modifier
            .fillMaxSize()
            .padding(padding)){
            PokemonSearchScreen(searchQuery)
            LazyColumn() {
                if (allPokemon != null) {
                    items(allPokemon.results.size) { x ->
                        if (searchQuery.value.isNotEmpty() && searchQuery.value.length >= 3 ) {
                            val pokemon = allPokemon.results[x]

                            if (pokemon.name?.contains(
                                    searchQuery.value,
                                    ignoreCase = true
                                ) == true
                            ) {
                                RenderPokemon(pokemon,viewModel)
                            }


                        }else{
                            val pokemon = allPokemon.results[x]
                            RenderPokemon(pokemon,viewModel)

                        }
                }
            }
        }
        }
    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonScreen(viewModel: PokeListViewModel, name: String, closeClick: () -> Unit) {
    val pokemon = viewModel.pokemonDetail.value
    viewModel.fetchPokemonDetail(name)
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
                }
            }
        }
    }
}

@Composable
fun RenderPokemon(pokeResponse: PokeResponse, viewModel: PokeListViewModel) {
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
                            text = pokeResponse.name.uppercase() ?: "No Title",
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
    val name = pokeResponse.name
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
