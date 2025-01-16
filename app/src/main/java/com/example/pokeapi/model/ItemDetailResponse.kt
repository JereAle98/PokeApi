package com.example.pokeapi.model

import com.google.gson.annotations.SerializedName

data class ItemDetailResponse(
    val name: String,
    val category: Category,
    val effect_entries: List<Effects>,
    val flavor_text_entries: List<Text>,
    val sprites: Sprites
)

data class Category(
    val name: String,
    val url: String
)

data class EffectEntries(
    val effects: Effects
)
data class Effects(
    @SerializedName("effect")val effect: String,
    @SerializedName("short_effect")val short_effect: String
)

data class Text(
    val text: String
)

data class Sprites(
    val default: String
)