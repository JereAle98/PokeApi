package com.example.pokeapi.data

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "pokemon")
data class PokeData(
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "url")
    val url: String
)
