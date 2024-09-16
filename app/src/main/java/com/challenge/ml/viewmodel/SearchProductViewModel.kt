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
    private val _product = MutableStateFlow<Resource<Products>>(Resource.Initial())
    val product: StateFlow<Resource<Products>> get() = _product

    fun fetchSearchProduct(query: String) {
        _product.value = Resource.Loading()
        viewModelScope.launch {
            try {
                when (val result = searchUseCase(query)) {
                    is Resource.Success -> {
                        if (result.data.products.isEmpty()) _product.value =
                            Resource.Failure(Exception("No se encontraron productos"))
                        else _product.value = Resource.Success(result.data)
                    }

                    is Resource.Failure -> _product.value = Resource.Failure(result.exception)

                    is Resource.Initial -> _product.value = Resource.Initial()

                    else -> {}
                }
            } catch (e: Exception) {
                Log.e("SearchProductViewModel", "Error al obtener productos", e)
                _product.value = Resource.Failure(e)
            }
        }
    }
}