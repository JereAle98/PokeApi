package com.example.pokeapi.data

import com.example.pokeapi.data.database.PokeData
import com.example.pokeapi.model.FormResponse
import com.example.pokeapi.model.PokeDetailResponse
import com.example.pokeapi.data.database.PokeDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokeRepository @Inject constructor(
    private val api: PokeApiService,
    private val pokeDao: PokeDao
) {

    fun getAllPokemon(): Flow<Resource<List<PokeData>>> = flow{
        emit(Resource.Loading())
        try {
            val response = api.getPokemon()
            if(response.isSuccessful){
                response.body()?.let { pokeResponse->
                    pokeDao.insertAll(pokeResponse.results)
                    emit(Resource.Success(pokeDao.getAll().first()))
                }
            }
        }catch (e: Exception){
            emit(Resource.Error(e.message ?: "An error occurred"))
        }
    }

    suspend fun deleteAllPokeData(allPokeData: List<PokeData>) = pokeDao.deleteAllPokeData(allPokeData)

    suspend fun getPokemonByName(name: String): PokeDetailResponse? {
        val response = api.getDetailPokemon(name)
        if (response.isSuccessful){
            return response.body()
        }
        return null
    }

    suspend fun getPokemonImage(name: String): FormResponse?{
        val response = api.getPokemonImage(name)
        if (response.isSuccessful){
            return response.body()
        }
        return null
    }

}