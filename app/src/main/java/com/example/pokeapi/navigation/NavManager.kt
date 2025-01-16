package com.example.pokeapi.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pokeapi.view.itemScreen.itemListScreen.ItemDetailScreen
import com.example.pokeapi.view.itemScreen.itemListScreen.ItemListView
import com.example.pokeapi.view.pokemonScreen.pokemonListScreen.PokeListView


@Composable
fun NavManager(navHostController: NavHostController, paddingValues: PaddingValues){
    NavHost(navController = navHostController, startDestination = Routes.PokemonView.route){
        composable(Routes.PokemonView.route) {
            PokeListView(paddingValues = paddingValues)
        }
        composable(Routes.ItemView.route) {
            ItemListView(paddingValues = paddingValues, navController = navHostController)
        }
        composable("detail/{name}"){
            val name = it.arguments?.getString("name") ?: ""
            ItemDetailScreen(name = name, paddingValues = paddingValues)
        }

    }
}