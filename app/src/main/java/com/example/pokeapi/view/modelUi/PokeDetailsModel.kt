package com.example.pokeapi.view.modelUi

import com.example.pokeapi.model.PokeAbilitiesResponse
import com.example.pokeapi.model.PokeCriesResponse
import com.example.pokeapi.model.PokeTypesResponse

data class PokeDetailsModel(
    val name: String,
    val abilities: List<PokeAbilitiesResponse>,
    val types: List<PokeTypesResponse>,
    val experience: Int,
    val height: Int,
    val weight: Int,
    val cries: PokeCriesResponse
)
//
//data class AbilitiesModel(
//    val ability: String,
//    val url: String
//)
//
//data class TypesModel(
//    val slot: Int,
//    val types: List<TypeModel>
//)
//
//data class TypeModel(
//    val name: String,
//    val url: String
//)