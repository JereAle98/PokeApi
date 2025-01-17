package com.example.pokeapi.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PokeData::class, ItemData::class, AbilityData::class], version = 4)
abstract class PokeDatabase: RoomDatabase() {
    abstract fun pokeDao(): PokeDao
    abstract fun itemDao(): ItemDao
    abstract fun abilityDao(): AbilityDao
}