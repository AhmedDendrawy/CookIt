package com.ahmed.cookit.repository

import android.util.Log
import com.ahmed.cookit.api.MealApi
import com.ahmed.cookit.database.CategoryDao
import com.ahmed.cookit.database.CategoryEntity
import com.ahmed.cookit.mapper.toCategoryEntityList
import kotlinx.coroutines.flow.Flow

class CategoryRepository(
    private val api: MealApi,
    private val dao: CategoryDao
) {

    fun getCategoriesFromRoom(): Flow<List<CategoryEntity>> {
        return dao.getCategories()
    }

    suspend fun refreshCategories() {
        try {
            val categoryEntities = api.getCategories().categories.toCategoryEntityList()
            dao.insertCategory(categoryEntities)
        } catch (e: Exception) {
            Log.d("Repo", e.message.toString())
        }
    }
}