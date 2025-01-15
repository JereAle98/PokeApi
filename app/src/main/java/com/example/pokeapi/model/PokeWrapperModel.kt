package com.example.pokeapi.model

import androidx.compose.runtime.MutableState
import com.example.pokeapi.data.response.PokeResponse

data class PokeWrapperModel(
    val count:Int,
    val prev:String?,
    val next:String?,
    val results:List<PokeResponse>
)
