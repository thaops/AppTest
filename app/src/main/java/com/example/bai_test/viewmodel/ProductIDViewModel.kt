package com.example.bai_test.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bai_test.data.model.Product
import com.example.bai_test.data.network.RetrofitInstance
import kotlinx.coroutines.launch

class ProductIDViewModel : ViewModel() {
    private val _product = mutableStateOf<Product?>(null)
    val product: State<Product?> = _product

    fun fetchProductById(id: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.apiService.getProductById(id)
                _product.value = response
                Log.e("TAG", "value response : ${response.toString()}")
            } catch (e: Exception) {
                // Handle error
                Log.e("TAG", "Error fetching product by ID: ${e.message}")
            }
        }
    }
}
