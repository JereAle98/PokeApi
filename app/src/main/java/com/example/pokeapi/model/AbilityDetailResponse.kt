package com.example.pokeapi.model

data class AbilityDetailResponse(
    val name: String,
    val effect_entries: List<VerboseEffect>
)

data class VerboseEffect(
    val effect: String
)
