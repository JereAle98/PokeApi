package com.example.pokeapi.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.rememberNavController
import com.example.pokeapi.R
import com.example.pokeapi.navigation.NavManager
import com.example.pokeapi.navigation.Routes
import com.example.pokeapi.ui.theme.principal
import com.example.pokeapi.view.components.BottomNav

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeView(){
    val navController = rememberNavController()
    val navigationRoutes = listOf(
        Routes.PokemonView,
        Routes.ItemView
    )

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = { },
                title = {
                    Image(
                        painter = painterResource(id = R.drawable.pokeapi_256),
                        contentDescription = null,
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )
        },
        bottomBar ={
            BottomNav(navController, navigationRoutes )
        }
    ){padding ->
        NavManager(navController, padding)
    }
}