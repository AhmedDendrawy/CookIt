package com.ahmed.cookit.api

import com.ahmed.cookit.model.CategoryResponse
import com.ahmed.cookit.model.MealResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {
    @GET("categories.php")
    suspend fun getCategories(): CategoryResponse

    @GET("filter.php")
    suspend fun getMealsByCategory(@Query("c") categoryName: String): MealResponse
}