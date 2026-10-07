package com.ahmed.cookit.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ahmed.cookit.model.State
import com.ahmed.cookit.ui.CategoriesRow
import com.ahmed.cookit.ui.MealsGrid

@Composable
fun CookItScreen(modifier: Modifier = Modifier, viewModel: CookItViewModel = viewModel()) {

    val categoriesState by viewModel.categoriesState.collectAsState()
    val mealsState by viewModel.mealState.collectAsState()
    val selectedCategory by viewModel.selectedCategory.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp, vertical = 16.dp)
    ) {

        when (categoriesState) {
            is State.Error -> Text("Error loading categories")
            State.Idle -> {}
            State.Loading -> CircularProgressIndicator()
            is State.Success -> {
                val categories = (categoriesState as State.Success).data
                CategoriesRow(
                    categoriesList = categories,
                    selectedCategoryName = selectedCategory,
                    onCategoryClick = { categoryName ->
                        viewModel.getMealsByCategory(categoryName)
                    })
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Box(modifier = Modifier
            .weight(1f)
            .fillMaxWidth()) {
            when (mealsState) {
                is State.Error -> {
                    Text("Error loading meals")
                }

                State.Idle -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "Select a category to show available meals", color = Color.Gray)
                    }
                }

                State.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                is State.Success -> {
                    val meals = (mealsState as State.Success).data
                    MealsGrid(mealsList = meals)
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CookItScreenPreview() {
    CookItScreen()
}