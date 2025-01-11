package com.example.pokeapi.data.response

import com.google.gson.annotations.SerializedName

data class PokeAbilitiesResponse (
    @SerializedName("ability") val ability: PokeAbilityResponse,
    @SerializedName("slot") val slot: Int
)


data class PokeAbilityResponse (
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)
