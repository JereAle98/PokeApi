package com.example.pokeapi.view.itemScreen.itemListScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pokeapi.data.database.ItemData
import com.example.pokeapi.data.Resource
import com.example.pokeapi.view.itemScreen.ItemViewModel

@Composable
fun ItemListView(itemViewModel: ItemViewModel = hiltViewModel(), paddingValues: PaddingValues, navController: NavController) {
    val searchQuery = remember { mutableStateOf("") }

    AllItemScreen(itemViewModel, searchQuery,paddingValues,navController )
}

@Composable
fun AllItemScreen(viewModel: ItemViewModel, searchQuery: MutableState<String>, paddingValues: PaddingValues, navController: NavController) {

    val allItem by viewModel.allItem.collectAsState()

    when (allItem) {
        is Resource.Loading -> CircularProgressIndicator()
        is Resource.Success -> {
            val itemList = (allItem as Resource.Success<List<ItemData>>).data

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                ItemSearchScreen(searchQuery)
                LazyColumn() {
                    if (itemList != null) {
                        items(itemList.size) { item ->
                            if (searchQuery.value.isNotEmpty() && searchQuery.value.length >= 3) {
                                val item = itemList[item]

                                if (item.name.contains(
                                        searchQuery.value,
                                        ignoreCase = true
                                    )
                                ) {
                                    RenderItem(item, navController)
                                }


                            } else {
                                val item = itemList[item]
                                RenderItem(item, navController)
                            }
                        }
                    }
                }
            }

        }

        is Resource.Error -> {
            (allItem as Resource.Error).message?.let {
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


//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun ItemScreen(viewModel: PokeListViewModel, name: String, closeClick: () -> Unit) {
//    viewModel.fetchPokemonDetail(name)
//    viewModel.fetchPokemonImage(name)
//
//    val pokemon = viewModel.pokemonDetail.value
//    val pokemonImage = viewModel.pokemonImage.value
//
//
//    Box() {
//        BasicAlertDialog(onDismissRequest = {}) {
//
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .clip(RoundedCornerShape(10.dp))
//                    .background(Color.White)
//                    .padding(start = 15.dp, bottom = 15.dp, top = 15.dp)
//            ) {
//                Column() {
//                    Row(
//                        modifier = Modifier.fillMaxWidth(),
//                        horizontalArrangement = Arrangement.SpaceBetween,
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Text(text = name.uppercase())
//                        IconButton(onClick = { closeClick() }
//                        ) {
//                            Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
//                        }
//                    }
//
//                    pokemon?.let {
//                        // Mostrar tipos
//                        Text("Tipos: ${it.types.joinToString { type -> type.type.name }}")
//
//                        // Mostrar habilidades
//                        Text("Habilidades: ${it.abilities.joinToString { ability -> ability.ability.name }}")
//
//                        Spacer(modifier = Modifier.height(8.dp))
//
//                    }
//                    Row(horizontalArrangement = Arrangement.SpaceBetween) {
//                        pokemonImage?.let {
//
//                            Image(
//                                painter = rememberAsyncImagePainter(it.sprites.imageFront),
//                                contentDescription = null,
//                                modifier = Modifier.size(150.dp),
//                            )
//                            Image(
//                                painter = rememberAsyncImagePainter(it.sprites.imageBack),
//                                contentDescription = null,
//                                modifier = Modifier.size(150.dp),
//                            )
//                        }
//                    }
//                }
//            }
//        }
//    }
//}

@Composable
fun RenderItem(itemData: ItemData, navController: NavController) {
    ListItem(
        {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate("detail/${itemData.name}") }) {
                Row {
                    Column {
                        Text(
                            text = itemData.name.uppercase(),
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
//    val name = itemData.name
//    if (showAlert) {
//        ItemScreen(
//            viewModel = viewModel,
//            name,
//            closeClick = { showAlert = false }
//        )
//    }
}

@Composable
fun ItemSearchScreen(searchQuery: MutableState<String>) {
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