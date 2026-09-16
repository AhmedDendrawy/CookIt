package com.ahmed.cookit.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.ahmed.cookit.model.Category
import com.ahmed.cookit.model.MealModel

@Composable
fun CategoriesRow(
    categoriesList: List<Category>,
    selectedCategoryName: String?,
    onCategoryClick: (String) -> Unit
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(categoriesList) { category ->
            CategoryItem(
                category = category,
                isSelected = selectedCategoryName == category.name,
                onItemClick = { onCategoryClick(category.name) }
            )
        }
    }
}

@Composable
fun MealsGrid(mealsList: List<MealModel>) {
    val configuration = LocalConfiguration.current
    val columnsCount = if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) 2 else 4

    LazyVerticalGrid(
        columns = GridCells.Fixed(columnsCount),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        items(mealsList) { meal ->
            MealItem(meal = meal)
        }
    }
}