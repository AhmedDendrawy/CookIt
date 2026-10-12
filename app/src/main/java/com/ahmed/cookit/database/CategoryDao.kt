package com.ahmed.cookit.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(categories: List<CategoryEntity>)

    @Query("SELECT * FROM category")
    fun getCategories() : Flow <List<CategoryEntity>>

}