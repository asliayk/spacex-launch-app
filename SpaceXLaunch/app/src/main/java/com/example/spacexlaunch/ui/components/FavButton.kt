package com.example.spacexlaunch.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.aymen.graphql.SearchLaunchQuery
import com.example.spacexlaunch.ui.viewmodels.FavoritesViewModel

@Composable
fun FavButton(
    model: FavoritesViewModel,
    launch: SearchLaunchQuery.Launch,
    isFav: Boolean
) {

    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        OutlinedButton(
            onClick = {
                if (!isFav) {
                    model.addFavorite(launch)
                } else {
                    model.deleteFavorite(launch)
                }
            },
            modifier = Modifier.size(40.dp),
            shape = CircleShape,
            border = if (isFav) BorderStroke(
                1.dp,
                Color.Red
            ) else BorderStroke(1.dp, Color.Black),
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                backgroundColor = if (isFav) Color.Red else Color.Black,
                contentColor = Color.White
            )
        ) {
            Icon(Icons.Default.Favorite, contentDescription = "")
        }


    }

}