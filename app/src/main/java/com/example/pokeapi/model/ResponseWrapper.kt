package com.example.pokeapi.model

import com.example.pokeapi.data.database.PokeData
import com.google.gson.annotations.SerializedName

data class ResponseWrapper(
    @SerializedName("count") val count:Int,
    @SerializedName("previous") val prev:String?,
    @SerializedName("next") val next:String?,
    @SerializedName("results") val results:List<PokeData>
){
    fun toPokeWrapperModel(): PokeWrapperModel{
        return PokeWrapperModel(
            count,
            prev,
            next,
            results
        )
    }
}

