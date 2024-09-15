package com.challenge.ml.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.challenge.domain.model.Products.Product

@Composable
fun ProductCard(
    product: Product,
    onItemClick: (Product) -> Unit,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier
            .padding(5.dp)
            .clickable { onItemClick(product) }
            .height(150.dp)
            .background(color = Color.White)
            .height(150.dp),
    ) {
        Row() {
            ProductImage(productImageUrl = product.pictureUrl)
            ProductDescription(product = product)
        }
    }
}

@Composable
private fun ProductDescription(product: Product) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 8.dp),
    ) {
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = product.title,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = product.price,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Normal),
                color = Color.Black,
            )
        }
    }
}
