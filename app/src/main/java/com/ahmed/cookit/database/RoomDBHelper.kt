package com.ahmed.cookit.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CategoryEntity::class], version = 1)
abstract class RoomDBHelper : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao

    companion object {

        @Volatile
        private var INSTANCE: RoomDBHelper? = null

        fun getInstance(c: Context): RoomDBHelper {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    c, RoomDBHelper::class.java,
                    "cook_it"
                )
                    .build()
                INSTANCE =instance
                return instance
            }
        }

    }
}