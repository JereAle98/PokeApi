package com.example.pokeapi.model

import com.example.pokeapi.data.database.PokeData

data class PokeWrapperModel(
    val count:Int,
    val prev:String?,
    val next:String?,
    val results:List<PokeData>
)
