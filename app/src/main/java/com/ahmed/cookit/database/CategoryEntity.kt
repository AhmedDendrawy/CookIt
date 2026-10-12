package com.ahmed.cookit.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class CategoryEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "_id")
    val id: String,

    val name: String,

    val imageURL: String
)
