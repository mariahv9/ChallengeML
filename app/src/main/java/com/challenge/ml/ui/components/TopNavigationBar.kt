package com.challenge.ml.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.challenge.ml.ui.theme.MainColor

@Composable
fun TopNavigationBar(
    navController: NavController,
    back: Boolean = false,
    searchBar: @Composable () -> Unit
) {
    TopAppBar(
        modifier = Modifier
            .padding(bottom = 10.dp)
            .height(100.dp),
        navigationIcon = {
            IconButton(onClick = { if (back) navController.popBackStack() }) {
                if (back) {
                    Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = null)
                } else {
                    Icon(imageVector = Icons.Rounded.Menu, contentDescription = null)
                }
            }
        },
        title = {
            searchBar()
        },
        backgroundColor = MainColor
    )
}
