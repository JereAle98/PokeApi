package com.example.pokeapi.model

import com.example.pokeapi.data.database.ItemData
import com.google.gson.annotations.SerializedName

data class ItemResponse(
    @SerializedName("count") val count:Int,
    @SerializedName("previous") val prev:String?,
    @SerializedName("next") val next:String?,
    @SerializedName("results") val results:List<ItemData>
)
