package com.example.pokeapi.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pokeapi.view.modelUi.PokeModel
import com.google.gson.annotations.SerializedName

@Entity(tableName = "pokemon")
data class PokeData(
    @PrimaryKey
    @ColumnInfo(name = "name")
    @SerializedName("name") val name: String,

    @ColumnInfo(name = "url")
    @SerializedName("url")val url: String
){
    fun toPokeModel(): PokeModel{
        return PokeModel(
            name,url
        )
    }
}
