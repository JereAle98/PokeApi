package com.example.pokeapi.view.itemScreen.itemListScreen

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.pokeapi.view.itemScreen.ItemViewModel

@Composable
fun ItemDetailScreen(viewModel: ItemViewModel= hiltViewModel(), name: String, paddingValues: PaddingValues){
    val itemDetail by viewModel.itemDetail.collectAsState()
    LaunchedEffect(name) {
        viewModel.fetchItemDetail(name)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ){
        itemDetail?.let {itemDetail->
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                ) {

                // Cuadro con título y descripción breve
                Card(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = itemDetail.name.uppercase(), fontWeight = FontWeight.Bold, fontSize = 20.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "Category: ${itemDetail.category.name.uppercase()}", fontWeight = FontWeight.Light)
                        Text(text = itemDetail.effect_entries[0].effect, textAlign = TextAlign.Justify)
                    }
                }

                // Cuadro con imagen
                Card(
                    modifier = Modifier.size(100.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {

                    Image(
                        painter = rememberAsyncImagePainter(itemDetail.sprites.default),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,

                        modifier = Modifier.size(150.dp).fillMaxSize(),
                    )
                }
            }

            // Descripción detallada debajo
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Aquí va una descripción más detallada sobre el objeto. Este texto puede ser largo y explicar más sobre el objeto, sus características y cualquier otra información relevante.",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }

}