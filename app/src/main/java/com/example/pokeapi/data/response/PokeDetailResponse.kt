package com.example.pokeapi.data.response

import com.google.gson.annotations.SerializedName

data class PokeDetailResponse (
    @SerializedName("abilities") val abilities: List<PokeAbilitiesResponse>,
    @SerializedName("types") val types: List<PokeTypesResponse>
)