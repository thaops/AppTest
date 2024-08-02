package com.example.bai_test.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bai_test.data.model.Product
import com.example.bai_test.data.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    private val _products = mutableStateOf<List<Product>>(emptyList())
    val products: State<List<Product>>  = _products

     fun fetchProducts() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.apiService.getProducts()
                _products.value = response
                Log.e("TAG", "value response : ${response.toString()}")
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}
