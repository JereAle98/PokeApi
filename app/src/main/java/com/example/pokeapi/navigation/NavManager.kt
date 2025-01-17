package com.example.pokeapi.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pokeapi.view.abilityScreen.AbilityViewModel
import com.example.pokeapi.view.abilityScreen.abilityDetailScreen.AbilityDetailScreen
import com.example.pokeapi.view.abilityScreen.abilityListScreen.AbilityListView
import com.example.pokeapi.view.itemScreen.ItemViewModel
import com.example.pokeapi.view.itemScreen.itemDetailScreen.ItemDetailScreen
import com.example.pokeapi.view.itemScreen.itemListScreen.ItemListView
import com.example.pokeapi.view.pokemonScreen.PokeViewModel
import com.example.pokeapi.view.pokemonScreen.pokemonDetailScreen.PokemonDetailScreen
import com.example.pokeapi.view.pokemonScreen.pokemonListScreen.PokeListView


@Composable
fun NavManager(navController: NavHostController,pokeViewModel: PokeViewModel, itemViewModel: ItemViewModel,abilityViewModel: AbilityViewModel, paddingValues: PaddingValues){
    NavHost(navController = navController, startDestination = Routes.PokemonView.route){
        composable(Routes.PokemonView.route) {
            PokeListView(pokeViewModel,paddingValues, navController)
        }
        composable(Routes.ItemView.route) {
            ItemListView(itemViewModel,paddingValues, navController)
        }
        composable(Routes.AbilityView.route){
            AbilityListView(abilityViewModel,paddingValues, navController)
        }
        composable("itemDetail/{name}"){
            val name = it.arguments?.getString("name") ?: ""
            ItemDetailScreen(itemViewModel,name,paddingValues)
        }
        composable("pokemonDetail/{name}"){
            val name = it.arguments?.getString("name") ?: ""
            PokemonDetailScreen(pokeViewModel, abilityViewModel,name,paddingValues)
        }
        composable("abilityDetail/{name}"){
            val name = it.arguments?.getString("name") ?: ""
            AbilityDetailScreen(abilityViewModel,name,paddingValues)
        }


    }
}