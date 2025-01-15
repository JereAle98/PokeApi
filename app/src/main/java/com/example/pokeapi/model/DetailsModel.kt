package com.example.pokeapi.model

import com.example.pokeapi.data.response.PokeAbilitiesResponse
import com.example.pokeapi.data.response.PokeAbilityResponse
import com.example.pokeapi.data.response.PokeTypesResponse

data class DetailsModel(
    val abilities: List<PokeAbilitiesResponse>,
    val types: List<PokeTypesResponse>
)
