package com.ahmed.cookit.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahmed.cookit.api.RetrofitClient
import com.ahmed.cookit.model.Category
import com.ahmed.cookit.model.CategoryResponse
import com.ahmed.cookit.model.MealModel
import com.ahmed.cookit.model.MealResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun CookItScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    var categoriesList by rememberSaveable { mutableStateOf(emptyList<Category>()) }
    var mealsList by rememberSaveable { mutableStateOf(emptyList<MealModel>()) }
    var selectedCategoryName by rememberSaveable { mutableStateOf<String?>(null) }
    var isDataLoaded by rememberSaveable { mutableStateOf(false) }
    var isMealsLoading by rememberSaveable { mutableStateOf(false) }

    if (!isDataLoaded) {
        RetrofitClient.api.getCategories().enqueue(object : Callback<CategoryResponse> {
            override fun onResponse(call: Call<CategoryResponse>, response: Response<CategoryResponse>) {
                if (response.isSuccessful) {
                    categoriesList = response.body()?.categories ?: emptyList()
                }
            }
            override fun onFailure(call: Call<CategoryResponse>, t: Throwable) {
                Toast.makeText(context, "Failed: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
        isDataLoaded = true
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp, vertical = 16.dp)
    ) {
        CategoriesRow(
            categoriesList = categoriesList,
            selectedCategoryName = selectedCategoryName,
            onCategoryClick = { categoryName ->
                selectedCategoryName = categoryName
                mealsList = emptyList()
                isMealsLoading = true

                RetrofitClient.api.getMealsByCategory(categoryName).enqueue(object : Callback<MealResponse> {
                    override fun onResponse(call: Call<MealResponse>, response: Response<MealResponse>) {
                        isMealsLoading = false
                        if (response.isSuccessful) {
                            mealsList = response.body()?.meals ?: emptyList()
                        }
                    }
                    override fun onFailure(call: Call<MealResponse>, t: Throwable) {
                        isMealsLoading = false
                        Toast.makeText(context, "Failed: ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (selectedCategoryName == null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Select a category to show available meals", color = Color.Gray)
            }
        } else if (isMealsLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            MealsGrid(mealsList = mealsList)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CookItScreenPreview() {
    CookItScreen()
}