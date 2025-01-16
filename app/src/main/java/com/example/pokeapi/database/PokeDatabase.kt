package com.example.pokeapi.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pokeapi.data.PokeData

@Database(entities = [PokeData::class], version = 1)
abstract class PokeDatabase: RoomDatabase() {
    abstract fun pokeDao(): PokeDao

    companion object{
        @Volatile
        private var Instance: PokeDatabase? = null

        fun getPokeDatabase(context: Context): PokeDatabase{
            return Instance ?: synchronized(this){
                Room.databaseBuilder(
                    context = context,
                    klass = PokeDatabase::class.java,
                    name = "pokemon"
                )
                    .build()
                    .also { Instance = it}
            }
        }
    }

}