package com.example.pokeapi.model

data class TypesModel(
    val slot: Int,
    val types: List<TypeModel>
)

data class TypeModel(
    val name: String,
    val url: String
)
