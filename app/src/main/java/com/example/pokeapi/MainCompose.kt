package com.example.pokeapi

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.pokeapi.navigation.NavManager
import com.example.pokeapi.navigation.Routes
import com.example.pokeapi.ui.theme.PokeApiTheme
import com.example.pokeapi.view.abilityScreen.AbilityViewModel
import com.example.pokeapi.view.components.BottomNav
import com.example.pokeapi.view.components.TopBar
import com.example.pokeapi.view.itemScreen.ItemViewModel
import com.example.pokeapi.view.pokemonScreen.PokeViewModel

@Composable
fun MainCompose() {
    val navController = rememberNavController()
    val navigationRoutes = listOf(
        Routes.PokemonView,
        Routes.ItemView,
        Routes.AbilityView
    )
    PokeApiTheme {
        Scaffold(
            topBar = {
                TopBar()
            },
            bottomBar ={
                BottomNav(navController, navigationRoutes )
            }
        ){ padding ->
            val pokeViewModel: PokeViewModel = hiltViewModel()
            val itemViewModel: ItemViewModel = hiltViewModel()
            val abilityViewModel: AbilityViewModel = hiltViewModel()
            NavManager(navController,pokeViewModel,itemViewModel,abilityViewModel,padding)
        }
    }
}