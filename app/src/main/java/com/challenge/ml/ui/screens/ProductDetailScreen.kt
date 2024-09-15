package com.challenge.ml.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.challenge.common.Resource
import com.challenge.domain.model.ProductDetail
import com.challenge.ml.ui.components.ProductImages
import com.challenge.ml.ui.components.TopNavigationBar
import com.challenge.ml.viewmodel.DetailProductViewModel

@Composable
fun ProductDetailScreen(
    productId: String?,
    detailProductViewModel: DetailProductViewModel = hiltViewModel(),
    navController: NavController
) {
    val productDetailState by detailProductViewModel.productDetail.collectAsState()

    LaunchedEffect(productId) {
        productId?.let {
            detailProductViewModel.fetchProductDetail(it)
        }
    }

    Column {
        TopNavigationBar(navController = navController, back = true) {}

        when (productDetailState) {
            is Resource.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(48.dp),
                    color = Color.Black
                )
                Text(
                    text = "Cargando detalles...",
                    color = Color.Black,
                    modifier = Modifier
                        .padding(16.dp)
                )
            }

            is Resource.Failure -> {
                Text(
                    text = "Fallo al cargar detalles: ${(productDetailState as Resource.Failure).exception.message}",
                    color = Color.Red,
                    modifier = Modifier
                        .padding(16.dp)
                )
            }

            is Resource.Success -> {
                val productDetail = (productDetailState as Resource.Success<ProductDetail>).data
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    val price = productDetail.price
                    val freeShipping = productDetail.freeShipping
                    Text(
                        text = productDetail.title,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                    ProductImages(pictures = productDetail.picture)
                    Text(
                        text = "Precio: $price",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = if (freeShipping) {
                            "Disponible, envío gratis"
                        } else {
                            "No disponible"
                        },
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}
