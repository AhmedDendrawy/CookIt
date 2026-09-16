package com.ahmed.cookit.model

import com.google.gson.annotations.SerializedName

class MealResponse(
    @SerializedName("meals")
    val meals: List<MealModel>
)

class MealModel(
    @SerializedName("idMeal")
    val id: String,

    @SerializedName("strMeal")
    val name: String,

    @SerializedName("strMealThumb")
    val imageURL: String
)