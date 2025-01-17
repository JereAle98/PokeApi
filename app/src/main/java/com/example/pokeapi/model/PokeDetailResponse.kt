package com.example.pokeapi.model

import com.example.pokeapi.view.modelUi.PokeDetailsModel
import com.google.gson.annotations.SerializedName


data class PokeDetailResponse (
    @SerializedName("name") val name: String,
    @SerializedName("abilities") val abilities: List<PokeAbilitiesResponse>,
    @SerializedName("types") val types: List<PokeTypesResponse>,
    @SerializedName("base_experience") val experience: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("weight") val weight: Int,
    @SerializedName("cries") val cries: PokeCriesResponse
){
    fun toPokeDetailModel(): PokeDetailsModel {
        return PokeDetailsModel(
            name,
            abilities,
            types,
            experience,
            height,
            weight,
            cries
        )
    }
}
data class PokeCriesResponse(
    @SerializedName("latest") val latest: String,
    @SerializedName("legacy") val legacy: String
)
data class PokeAbilitiesResponse (
    @SerializedName("ability") val ability: PokeAbilityResponse,
    @SerializedName("slot") val slot: Int
)


data class PokeAbilityResponse (
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)

data class PokeTypesResponse(
    @SerializedName("slot") val slot: Int,
    @SerializedName("type") val type: TypeResponse
)

data class TypeResponse (
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)

data class FormResponse(
    @SerializedName("sprites") val sprites: ImageResponse

)

data class ImageResponse(
    @SerializedName("front_default") val imageFront: String,
    @SerializedName("back_default") val imageBack: String
)