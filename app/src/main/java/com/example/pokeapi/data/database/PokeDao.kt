package com.example.pokeapi.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PokeDao {
    @Query("SELECT * FROM pokemon")
    fun getAll(): Flow<List<PokeData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(allPokeData: List<PokeData>)

    @Delete
    suspend fun deleteAllPokeData(allPokeData: List<PokeData>)

}