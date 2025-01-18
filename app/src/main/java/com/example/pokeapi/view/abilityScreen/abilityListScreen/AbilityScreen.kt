package com.example.pokeapi.view.abilityScreen.abilityListScreen

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pokeapi.data.Resource
import com.example.pokeapi.data.database.AbilityData
import com.example.pokeapi.ui.theme.principal
import com.example.pokeapi.view.abilityScreen.AbilityViewModel

@Composable
fun AbilityListView(abilityViewModel: AbilityViewModel, paddingValues: PaddingValues, navController: NavController) {
    val searchQuery = remember { mutableStateOf("") }

    AllAbilityScreen(abilityViewModel, searchQuery,paddingValues,navController )
}

@Composable
fun AllAbilityScreen(viewModel: AbilityViewModel, searchQuery: MutableState<String>, paddingValues: PaddingValues, navController: NavController) {

    val allAbility by viewModel.allAbility.collectAsState()

    when (allAbility) {
        is Resource.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    modifier = Modifier.size(64.dp), color = Color.Gray
                )
            }
        }
        is Resource.Success -> {
            val abilityList = (allAbility as Resource.Success<List<AbilityData>>).data

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                ItemSearchScreen(searchQuery)
                LazyColumn() {
                    if (abilityList != null) {
                        items(abilityList.size) { ability ->
                            if (searchQuery.value.isNotEmpty()) {
                                val ability = abilityList[ability]

                                if (ability.name.contains(
                                        searchQuery.value,
                                        ignoreCase = true
                                    )
                                ) {
                                    RenderItem(ability, navController)
                                }


                            } else {
                                val ability = abilityList[ability]
                                RenderItem(ability, navController)
                            }
                        }
                    }
                }
            }

        }

        is Resource.Error -> {
            (allAbility as Resource.Error).message?.let {
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
fun RenderItem(abilityData: AbilityData, navController: NavController) {
    ListItem(
        {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate("abilityDetail/${abilityData.name}") }
            ) {
                Row {
                    Column {
                        Text(
                            text = abilityData.name.uppercase(),
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
            label = { Text("Enter Ability") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}