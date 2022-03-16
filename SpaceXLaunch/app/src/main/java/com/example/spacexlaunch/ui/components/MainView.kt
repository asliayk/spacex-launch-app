package com.example.spacexlaunch.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import com.example.spacexlaunch.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavHostController
import com.example.spacexlaunch.ui.viewmodels.FavoritesViewModel
import com.example.spacexlaunch.ui.viewmodels.HomeViewModel

@Composable
fun MainView(
    viewModel: HomeViewModel,
    navController: NavHostController,
    fviewModel: FavoritesViewModel,
) {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    val flaunches by fviewModel.readAllData.observeAsState()
    val launches by viewModel.lList.observeAsState()
    Scaffold(topBar = {
        TopAppBar(backgroundColor = Color.Black, title = {
            Text(text = "SpaceX Launch App", color = Color.White)
        }, navigationIcon = if (navController.previousBackStackEntry != null) {
            {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        } else {
            null
        })
    }) {
        Image(
            painter = painterResource(id = R.drawable.r),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Scaffold(backgroundColor = Color.Transparent) {
            Column() {
                SearchView(viewModel, textState)
                LaunchList(
                    navController = navController,
                    launches = launches,
                    flaunches = flaunches,
                    model = fviewModel
                )

            }
        }

    }
}