package com.ahmed.cookit.api

import com.ahmed.cookit.model.CategoryResponse
import com.ahmed.cookit.model.MealResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {
    @GET("categories.php")
    fun getCategories(): Call<CategoryResponse>

    @GET("filter.php")
    fun getMealsByCategory(@Query("c") categoryName: String): Call<MealResponse>
}