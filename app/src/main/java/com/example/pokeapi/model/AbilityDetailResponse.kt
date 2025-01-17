package com.example.pokeapi.model

data class AbilityDetailResponse(
    val name: String,
    val effect_entries: List<VerboseEffect>,
    val generation: Generation,
    val pokemon: List<ListPokemon>
)

data class VerboseEffect(
    val effect: String
)

data class Generation(
    val name: String
)

data class ListPokemon(
    val pokemon: Pokemon
)

data class Pokemon(
    val name: String
)


