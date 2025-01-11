package com.example.pokeapi.data.response

import com.google.gson.annotations.SerializedName

data class ResponseWrapper(
    @SerializedName("count") val count:Int,
    @SerializedName("previous") val prev:String?,
    @SerializedName("next") val next:String?,
    @SerializedName("results") val results:List<PokeResponse>
)
