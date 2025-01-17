package com.example.pokeapi.view.pokemonScreen.pokemonDetailScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.pokeapi.ui.theme.principal
import com.example.pokeapi.ui.theme.secundario
import com.example.pokeapi.ui.theme.tertario
import com.example.pokeapi.view.abilityScreen.AbilityViewModel
import com.example.pokeapi.view.abilityScreen.abilityDetailScreen.AbilityDetailScreen1
import com.example.pokeapi.view.abilityScreen.abilityDetailScreen.AbilityDetailScreen2
import com.example.pokeapi.view.pokemonScreen.PokeViewModel

@Composable
fun PokemonDetailScreen(viewModel: PokeViewModel,abilityViewModel: AbilityViewModel, name: String, paddingValues: PaddingValues){
    val pokemonDetail by viewModel.pokemonDetail.collectAsState()
    val pokemonImage = viewModel.pokemonImage.value

    LaunchedEffect(name) {
        viewModel.fetchPokemonDetail(name)
        viewModel.fetchPokemonImage(name)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(principal)
    ){
        pokemonDetail?.let {pokemonDetail->
            val height = pokemonDetail.height * 10
            val weight = (pokemonDetail.weight * 100)/1000
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {

                Card(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(width = 4.dp, color = secundario),
                    colors = CardDefaults.cardColors(tertario)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = pokemonDetail.name.uppercase(), fontWeight = FontWeight.ExtraBold, fontSize = 20.sp, color = Color.White)
                        Spacer(modifier = Modifier.height(8.dp))
                        pokemonDetail?.let {
                            Text(text = "Types: ${it.types.joinToString { type -> type.type.name.uppercase() }}", fontWeight = FontWeight.Light, color = Color.Gray)
                        }
                        Text(text = "Base Experience: ${pokemonDetail.experience} xp", fontWeight = FontWeight.Bold, color = Color.White)
                        Text(text = "Height: ${height} cm", textAlign = TextAlign.Justify,fontWeight = FontWeight.Bold, color = Color.White)
                        Text(text = "Weight: ${weight} kg", textAlign = TextAlign.Justify,fontWeight = FontWeight.Bold, color = Color.White)
                    }
                }

                Card(
                    modifier = Modifier.size(125.dp),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(width = 4.dp, color = secundario),
                    colors = CardDefaults.cardColors(tertario)
                ) {

                    Image(
                        painter = rememberAsyncImagePainter(pokemonImage?.sprites?.imageFront),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,

                        modifier = Modifier.size(150.dp).fillMaxSize(),
                    )
                }
            }
            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)){
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(width = 4.dp, color = secundario),
                    colors = CardDefaults.cardColors(tertario)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        pokemonDetail?.let {
                            val ability1 = it.abilities[0].ability.name
                            val ability2 = it.abilities[1].ability.name

                            Text(
                                text = "ABILITIES: ",
                                style = MaterialTheme.typography.bodyLarge,
                                textAlign = TextAlign.Justify,
                                color = Color.White,
                                fontWeight = FontWeight.Bold

                            )
                            AbilityDetailScreen1(abilityViewModel,ability1)
                            AbilityDetailScreen2(abilityViewModel,ability2)
                        }
                    }

                }
            }

        }
    }

}