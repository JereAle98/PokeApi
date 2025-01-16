package com.example.pokeapi.data

import com.example.pokeapi.model.FormResponse
import com.example.pokeapi.model.ItemDetailResponse
import com.example.pokeapi.model.ItemResponse
import com.example.pokeapi.model.PokeDetailResponse
import com.example.pokeapi.model.ResponseWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApiService {
    @GET("pokemon")
    suspend fun getPokemon(@Query ("limit") limit: Int = 1400): Response<ResponseWrapper>

    @GET("pokemon/{name}")
    suspend fun getDetailPokemon(@Path("name") name: String): Response<PokeDetailResponse>

    @GET("pokemon-form/{name}")
    suspend fun getPokemonImage(@Path("name") name: String): Response<FormResponse>

    @GET("item")
    suspend fun getItem(@Query ("limit") limit: Int = 2200): Response<ItemResponse>

    @GET("item/{name}")
    suspend fun getItemDetail(@Path("name")name: String): Response<ItemDetailResponse>

}
