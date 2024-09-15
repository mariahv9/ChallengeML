package com.challenge.ml.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.common.Resource
import com.challenge.domain.model.Products
import com.challenge.domain.usecase.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class SearchProductViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase
) : ViewModel() {
    private val _product = MutableStateFlow<Resource<Products>>(Resource.Loading())
    val product: StateFlow<Resource<Products>> get() = _product

    fun fetchSearchProduct(query: String) {
        viewModelScope.launch {
            _product.value = Resource.Loading()
            try {
                val result = searchUseCase(query)
                _product.value = result
            } catch (e: Exception) {
                Log.e("SearchProductViewModel", "Error fetching products", e)
                _product.value = Resource.Failure(e)
            }
        }
    }
}
