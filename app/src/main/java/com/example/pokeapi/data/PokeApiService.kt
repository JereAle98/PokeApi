package com.example.pokeapi.data

import com.example.pokeapi.data.response.PokeDetailResponse
import com.example.pokeapi.data.response.ResponseWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApiService {
    @GET("pokemon")
    suspend fun getPokemon(@Query("offset")page: Int): ResponseWrapper

    @GET("pokemon/{name}")
    suspend fun getDetailPokemon(@Path("name") name: String): Response<PokeDetailResponse>
}
