package com.example.pokeapi.view.itemScreen.itemDetailScreen

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
import com.example.pokeapi.view.itemScreen.ItemViewModel

@Composable
fun ItemDetailScreen(viewModel: ItemViewModel, name: String, paddingValues: PaddingValues){
    val itemDetail by viewModel.itemDetail.collectAsState()
    LaunchedEffect(name) {
        viewModel.fetchItemDetail(name)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(principal)
    ){
        itemDetail?.let {itemDetail->
            val effect = itemDetail.effect_entries[0].effect
            val result = effect.replace("\n", "")
            val text = itemDetail.flavor_text_entries[1].text
            val textModify = text.replace("\n", " ")

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
                        Text(text = itemDetail.name.uppercase(), fontWeight = FontWeight.ExtraBold, fontSize = 20.sp, color = Color.White)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "Category: ${itemDetail.category.name.uppercase()}", fontWeight = FontWeight.Light, color = Color.Gray)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = textModify, textAlign = TextAlign.Justify,fontWeight = FontWeight.Bold, color = Color.White)
                    }
                }

                Card(
                    modifier = Modifier.size(125.dp),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(width = 4.dp, color = secundario),
                    colors = CardDefaults.cardColors(tertario)
                ) {

                    Image(
                        painter = rememberAsyncImagePainter(itemDetail.sprites.default),
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
                    LazyColumn (modifier = Modifier.padding(16.dp)) {
                        items(1){
                            Column() {
                                Text(
                                    text = "EFFECT: ${result}\n",
                                    style = MaterialTheme.typography.bodyLarge,
                                    textAlign = TextAlign.Justify,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "SHORT EFFECT: ${itemDetail.effect_entries[0].short_effect}",
                                    style = MaterialTheme.typography.bodyLarge,
                                    textAlign = TextAlign.Justify,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold

                                )
                            }
                        }
                    }
                }
            }
        }
    }
}