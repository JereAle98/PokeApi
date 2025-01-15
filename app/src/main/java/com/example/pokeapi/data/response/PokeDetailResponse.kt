package com.example.pokeapi.data.response

import com.example.pokeapi.model.DetailsModel
import com.example.pokeapi.model.PokeWrapperModel
import com.google.gson.annotations.SerializedName

data class PokeDetailResponse (
    @SerializedName("abilities") val abilities: List<PokeAbilitiesResponse>,
    @SerializedName("types") val types: List<PokeTypesResponse>
){
    fun toDetailsModel(): DetailsModel {
        return DetailsModel(
            abilities,
            types
        )
    }
}