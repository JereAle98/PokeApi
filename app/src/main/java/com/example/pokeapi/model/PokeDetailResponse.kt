package com.example.pokeapi.model

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