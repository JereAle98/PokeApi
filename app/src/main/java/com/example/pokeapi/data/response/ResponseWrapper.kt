package com.example.pokeapi.data.response

import com.example.pokeapi.model.PokeWrapperModel
import com.google.gson.annotations.SerializedName

data class ResponseWrapper(
    @SerializedName("count") val count:Int,
    @SerializedName("previous") val prev:String?,
    @SerializedName("next") val next:String?,
    @SerializedName("results") val results:List<PokeResponse>
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

