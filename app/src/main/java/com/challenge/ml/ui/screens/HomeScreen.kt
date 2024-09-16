package com.challenge.ml.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.challenge.common.Resource
import com.challenge.domain.model.Products
import com.challenge.ml.ui.components.ProductList
import com.challenge.ml.ui.components.SearchBar
import com.challenge.ml.ui.components.TopNavigationBar
import com.challenge.ml.viewmodel.SearchProductViewModel

@Composable
fun HomeScreen(
    searchProductViewModel: SearchProductViewModel = hiltViewModel(),
    navController: NavController
) {
    val uiState by searchProductViewModel.product.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        TopNavigationBar(navController = navController, back = false) {
            SearchBar(viewModel = searchProductViewModel)
        }

        when (uiState) {
            is Resource.Initial -> {
                Text(
                    text = "¡Bienvenido! ¿Qué producto buscas hoy?",
                    color = Color.Black,
                    modifier = Modifier
                        .padding(16.dp)
                )
            }

            is Resource.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(48.dp)
                        .align(Alignment.CenterHorizontally),
                    color = Color.Black
                )
                Text(
                    text = "Cargando...",
                    color = Color.Black,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp)
                )
            }

            is Resource.Failure -> {
                Text(
                    text = "Falló al cargar los productos",
                    color = Color.Red,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp)
                )
            }

            is Resource.Success -> {
                val products = (uiState as Resource.Success<Products>).data
                ProductList(
                    modifier = Modifier.fillMaxSize(),
                    onClick = { productId ->
                        navController.navigate("productDetail/$productId")
                    },
                    products = products
                )
            }
        }
    }
}
