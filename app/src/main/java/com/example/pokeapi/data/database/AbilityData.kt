package com.example.pokeapi.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "ability")
data class AbilityData(
@PrimaryKey
@ColumnInfo(name = "name")
@SerializedName("name") val name: String,

@ColumnInfo(name = "url")
@SerializedName("url")val url: String
)
