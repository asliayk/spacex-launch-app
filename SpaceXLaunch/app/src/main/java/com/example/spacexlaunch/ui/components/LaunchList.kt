package com.example.spacexlaunch.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.aymen.graphql.SearchLaunchQuery
import com.example.spacexlaunch.data.Launch
import com.example.spacexlaunch.ui.viewmodels.FavoritesViewModel

@Composable
fun LaunchList(
    navController: NavHostController,
    launches: List<SearchLaunchQuery.Launch?>?,
    flaunches: List<Launch>?,
    model: FavoritesViewModel
) {

    LazyColumn {
        if (flaunches != null) {
            items(flaunches) { launch ->
                val nlaunch = launch.missionName.let {
                    SearchLaunchQuery.Launch(
                        launch.id,
                        launch.details,
                        launch.launchSite,
                        launch.launchDateLocal.toString(),
                        launch.launchDateUnix,
                        launch.launchDateUtc,
                        launch.launchSuccess,
                        launch.links,
                        launch.missionName,
                        launch.mission_id,
                        launch.rocket,
                        launch.ships
                    )
                }
                LaunchCard(
                    navController = navController,
                    launch = nlaunch,
                    isFav = true,
                    model = model
                )
            }
        }

        if (launches != null) {
            items(launches) { launch ->
                if (launch != null && (flaunches != null && !flaunches.any { it.missionName == launch.mission_name })) {
                    LaunchCard(
                        navController = navController,
                        launch = launch,
                        isFav = false,
                        model = model
                    )
                }
            }
        }

    }
}