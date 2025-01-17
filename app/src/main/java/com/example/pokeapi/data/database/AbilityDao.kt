package com.example.pokeapi.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AbilityDao {
    @Query("SELECT * FROM ability")
    fun getAll(): Flow<List<AbilityData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(allItemData: List<AbilityData>)

    @Delete
    suspend fun deleteAllItemData(allItemData: List<AbilityData>)
}