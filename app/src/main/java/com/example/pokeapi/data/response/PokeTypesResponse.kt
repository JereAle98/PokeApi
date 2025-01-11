package com.example.pokeapi.data.response

import com.google.gson.annotations.SerializedName

data class PokeTypesResponse(
    @SerializedName("slot") val slot: Int,
    @SerializedName("type") val type: TypeResponse
)

data class TypeResponse (
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)