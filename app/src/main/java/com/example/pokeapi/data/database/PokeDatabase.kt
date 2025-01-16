package com.example.pokeapi.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PokeData::class, ItemData::class], version = 3)
abstract class PokeDatabase: RoomDatabase() {
    abstract fun pokeDao(): PokeDao
    abstract fun itemDao(): ItemDao
}