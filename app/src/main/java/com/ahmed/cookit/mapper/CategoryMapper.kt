package com.ahmed.cookit.mapper

import com.ahmed.cookit.database.CategoryEntity
import com.ahmed.cookit.model.CategoryDto

fun CategoryDto.toCategoryEntity(): CategoryEntity{
    return CategoryEntity(
        id = this.id,
        name = this.name,
        imageURL=this.imageURL
    )
}

fun List<CategoryDto>.toCategoryEntityList(): List<CategoryEntity> {
    return this.map { it.toCategoryEntity() }
}