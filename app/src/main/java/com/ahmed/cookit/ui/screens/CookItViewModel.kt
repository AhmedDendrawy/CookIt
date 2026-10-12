package com.ahmed.cookit.ui.screens

import android.app.Application
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmed.cookit.api.RetrofitClient
import com.ahmed.cookit.database.CategoryEntity
import com.ahmed.cookit.database.RoomDBHelper
import com.ahmed.cookit.model.CategoryDto
import com.ahmed.cookit.model.MealModel
import com.ahmed.cookit.model.State
import com.ahmed.cookit.repository.CategoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CookItViewModel(app: Application) : AndroidViewModel(app) {
    private val repository: CategoryRepository


    private val _categoriesState = MutableStateFlow<State<List<CategoryEntity>>>(State.Loading)
    val categoriesState = _categoriesState.asStateFlow()

    private val _mealState = MutableStateFlow<State<List<MealModel>>>(State.Idle)
    val mealState = _mealState.asStateFlow()

    private val _selectedCategory = MutableStateFlow<String?>(null)
    val selectedCategory = _selectedCategory.asStateFlow()

    init {
        val database = RoomDBHelper.getInstance(app)
        val api = RetrofitClient.api
        repository = CategoryRepository(api, database.categoryDao())
        categoriesState()
    }

    private fun categoriesState() {
        viewModelScope.launch {
            repository.refreshCategories()
        }

        viewModelScope.launch {
            repository.getCategoriesFromRoom().collect { categoriesList ->
                if (categoriesList.isEmpty()) {
                    _categoriesState.value = State.Loading
                } else {
                    _categoriesState.value = State.Success(categoriesList)
                }

            }
        }
    }


    fun getMealsByCategory(categoryName: String) {
        _selectedCategory.value = categoryName
        viewModelScope.launch {
            _mealState.value = State.Loading
            try {
                val result = RetrofitClient.api.getMealsByCategory(categoryName)
                _mealState.value = State.Success(result.meals ?: emptyList())
            } catch (e: Exception) {
                _mealState.value = State.Error(e.message.toString())
            }
        }
    }

}