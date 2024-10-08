package com.challenge.ml.ui.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.challenge.ml.R

@Composable
fun ProductImage(productImageUrl: String) {
    SubcomposeAsyncImage(
        model = productImageUrl,
        contentDescription = stringResource(id = R.string.description_image),
        modifier = Modifier
            .fillMaxHeight()
            .width(130.dp),
        loading = {
            CircularProgressIndicator(
                color = Color.Black
            )
        }
    )
}
