package com.example.pokeapi.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.pokeapi.data.response.PokeDetailResponse
import com.example.pokeapi.data.response.PokeResponse
import com.example.pokeapi.data.response.ResponseWrapper
import com.example.pokeapi.database.PokeDao
import com.example.pokeapi.model.DetailsModel
import com.example.pokeapi.model.PokeModel
import com.example.pokeapi.model.PokeWrapperModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class PokeRepository @Inject constructor(
    private val api: PokeApiService,
    private val pokeDao: PokeDao
) {
    fun getAll() = pokeDao.getAll()

    suspend fun insertPoke(pokeData: PokeData) = pokeDao.insertFriend(pokeData)

    suspend fun deleteAllPoke(allPoke: List<PokeData>) = pokeDao.deleteAllPokeData(allPoke)

    suspend fun getAllPokemons(): ResponseWrapper? {
        val response = api.getPokemon()
        if (response.isSuccessful) {

            return response.body()
        }
        return null
    }

    suspend fun getPokemonByName(name: String): PokeDetailResponse? {
        val response = api.getDetailPokemon(name)
        if (response.isSuccessful){
            return response.body()
        }
        return null
    }

}