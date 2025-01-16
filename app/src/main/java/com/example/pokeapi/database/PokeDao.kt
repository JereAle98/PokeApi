package com.example.pokeapi.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import com.example.pokeapi.data.PokeData
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PokeDao {
    @Query("SELECT * FROM pokemon")
    fun getAll(): Flow<List<PokeData>>

    @Insert
    suspend fun insertFriend(pokeData: PokeData)

    @Delete
    suspend fun deleteAllPokeData(allPokeData: List<PokeData>)

}