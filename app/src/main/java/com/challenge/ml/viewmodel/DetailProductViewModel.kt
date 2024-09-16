package com.challenge.ml.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.common.Resource
import com.challenge.domain.model.ProductDetail
import com.challenge.domain.usecase.DetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class DetailProductViewModel @Inject constructor(
    private val detailUseCase: DetailUseCase
) : ViewModel() {
    private val _productDetail = MutableStateFlow<Resource<ProductDetail>>(Resource.Loading())
    val productDetail: StateFlow<Resource<ProductDetail>> get() = _productDetail

    fun fetchProductDetail(productId: String) {
        viewModelScope.launch {
            _productDetail.value = Resource.Loading()
            try {
                when (val result = detailUseCase(productId)) {
                    is Resource.Success -> {
                        if (result.data.title.isEmpty()) _productDetail.value =
                            Resource.Failure(Exception("No se encontro detalle del producto"))
                        else _productDetail.value = Resource.Success(result.data)
                    }

                    is Resource.Failure -> _productDetail.value = Resource.Failure(result.exception)

                    is Resource.Initial -> _productDetail.value = Resource.Initial()

                    else -> {}
                }
            } catch (e: Exception) {
                _productDetail.value = Resource.Failure(e)
            }
        }
    }
}
