package com.example.spacexlaunch


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.spacexlaunch.ui.components.LaunchView
import com.example.spacexlaunch.ui.components.MainView
import com.example.spacexlaunch.ui.theme.custTypography
import com.example.spacexlaunch.ui.viewmodels.FavoritesViewModel
import com.example.spacexlaunch.ui.viewmodels.HomeViewModel

class MainActivity : ComponentActivity() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var favViewModel: FavoritesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        favViewModel = ViewModelProvider(this)[FavoritesViewModel::class.java]

        setContent {
            MaterialTheme(typography = custTypography) {
                HomeView(model = homeViewModel, favViewModel = favViewModel)
            }
        }
    }
}


@Composable
fun HomeView(model: HomeViewModel = viewModel(), favViewModel: FavoritesViewModel) {
    val navController = rememberNavController()


    Scaffold(modifier = Modifier.fillMaxSize()) {
        NavHost(navController = navController, startDestination = "main") {
            composable("main") {
                MainView(
                    model, navController, favViewModel
                )
            }
            composable("launch/{name}") {
                val name = it.arguments?.getString("name")
                if (name != null) {
                    LaunchView(model, favViewModel, navController, name = name)
                }
            }
        }
    }
}







