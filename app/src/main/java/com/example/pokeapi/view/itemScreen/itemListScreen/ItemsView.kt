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
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pokeapi.data.database.ItemData
import com.example.pokeapi.data.Resource
import com.example.pokeapi.ui.theme.principal
import com.example.pokeapi.view.itemScreen.ItemViewModel

@Composable
fun ItemListView(itemViewModel: ItemViewModel, paddingValues: PaddingValues, navController: NavController) {
    val searchQuery = remember { mutableStateOf("") }

    AllItemScreen(itemViewModel, searchQuery,paddingValues,navController )
}

@Composable
fun AllItemScreen(viewModel: ItemViewModel, searchQuery: MutableState<String>, paddingValues: PaddingValues, navController: NavController) {

    val allItem by viewModel.allItem.collectAsState()

    when (allItem) {
        is Resource.Loading ->{
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    modifier = Modifier.size(64.dp), color = Color.Gray
                )
            }
        }
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
                            if (searchQuery.value.isNotEmpty()) {
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
                        .background(principal)
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = it, textAlign = TextAlign.Center, color = Color.White)
                }
            }
        }
    }

}

@Composable
fun RenderItem(itemData: ItemData, navController: NavController) {
    ListItem(
        {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate("itemDetail/${itemData.name}") }) {
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
            label = { Text("Enter Item") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}