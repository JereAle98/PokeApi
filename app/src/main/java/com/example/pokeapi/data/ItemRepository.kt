package com.example.pokeapi.data

import com.example.pokeapi.data.database.ItemData
import com.example.pokeapi.data.database.ItemDao
import com.example.pokeapi.model.FormResponse
import com.example.pokeapi.model.ItemDetailResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ItemRepository@Inject constructor(
    private val api: PokeApiService,
    private val itemDao: ItemDao
) {

    fun getAllItem(): Flow<Resource<List<ItemData>>> = flow{
        emit(Resource.Loading())
        try {
            val response = api.getItem()
            if(response.isSuccessful){
                response.body()?.let { itemResponse->
                    itemDao.insertAll(itemResponse.results)
                }
            }
            emit(Resource.Success(itemDao.getAll().first()))
        }catch (e: Exception){
            emit(Resource.Error(e.message ?: "An error occurred"))
        }
    }

    suspend fun deleteAllPokeData(allItemData: List<ItemData>) = itemDao.deleteAllItemData(allItemData)

    suspend fun getItemDetail(name: String): ItemDetailResponse?{
        val response =  api.getItemDetail(name)
        if(response.isSuccessful){
            return response.body()
        }
        return null
    }

}