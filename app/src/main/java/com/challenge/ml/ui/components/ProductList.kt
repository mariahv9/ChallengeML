package com.challenge.ml.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.challenge.domain.model.Products

@Composable
fun ProductList(
    modifier: Modifier,
    products: Products,
    onClick: (String) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(products.products) { product ->
            ProductCard(
                product = product,
                onItemClick = {
                    onClick(it.id)
                }
            )
        }
    }
}

