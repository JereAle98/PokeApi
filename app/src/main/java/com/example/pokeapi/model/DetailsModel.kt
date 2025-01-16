package com.example.pokeapi.model

data class DetailsModel(
    val abilities: List<PokeAbilitiesResponse>,
    val types: List<PokeTypesResponse>
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