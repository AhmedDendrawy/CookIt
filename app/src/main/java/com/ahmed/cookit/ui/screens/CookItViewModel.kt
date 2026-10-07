package com.ahmed.cookit.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmed.cookit.api.RetrofitClient
import com.ahmed.cookit.model.Category
import com.ahmed.cookit.model.MealModel
import com.ahmed.cookit.model.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CookItViewModel : ViewModel() {
    private val _categoriesState = MutableStateFlow<State<List<Category>>>(State.Loading)
    val categoriesState = _categoriesState.asStateFlow()

    private val _mealState = MutableStateFlow<State<List<MealModel>>>(State.Idle)
    val mealState = _mealState.asStateFlow()

    private val _selectedCategory = MutableStateFlow<String?>(null)
    val selectedCategory = _selectedCategory.asStateFlow()

    init {
        categoriesState()
    }

    private fun categoriesState() {
        viewModelScope.launch {
            _categoriesState.value = State.Loading
            try {
                val result = RetrofitClient.api.getCategories()
                _categoriesState.value = State.Success(result.categories)
            } catch (e: Exception) {
                _categoriesState.value = State.Error(e.message.toString())

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
            }
            catch (e: Exception){
                _mealState.value= State.Error(e.message.toString())
            }
        }
    }

}