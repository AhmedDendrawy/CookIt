package com.ahmed.cookit.model

import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName("categories")
    val categories: List<Category>
)

data class Category (
    @SerializedName("idCategory")
    val id: String,

    @SerializedName("strCategory")
    val name: String,

    @SerializedName("strCategoryThumb")
    val imageURL: String)