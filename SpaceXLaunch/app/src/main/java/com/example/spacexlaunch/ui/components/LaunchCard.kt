package com.example.spacexlaunch.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.spacexlaunch.R
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.aymen.graphql.SearchLaunchQuery
import com.example.spacexlaunch.ui.viewmodels.FavoritesViewModel

@Composable
fun LaunchCard(
    navController: NavHostController,
    launch: SearchLaunchQuery.Launch,
    model: FavoritesViewModel,
    isFav: Boolean
) {

    var argument = launch.mission_name
    if (argument != null) {
        if (argument.contains("/")) {
            argument = argument.replace("/", "99")
        }
    }
    Card(elevation = 4.dp,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { navController.navigate("launch/${argument}") }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(5.dp)
                .padding(2.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(90.dp)
                    .padding(top = 10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_rocket),
                    contentDescription = "",

                    modifier = Modifier
                        .size(50.dp)
                        .padding(end = 5.dp, bottom = 5.dp)

                )
                if (launch.launch_date_local != null) {
                    val times = launch.launch_date_local.toString().split("T")

                    Text(
                        text = times[0],
                        fontSize = 10.sp,
                        color = Color.Gray,
                        modifier = Modifier
                            .padding(2.dp)
                            .wrapContentSize()
                    )
                    Text(
                        text = times[1],
                        fontSize = 10.sp,
                        color = Color.Gray,
                        modifier = Modifier
                            .padding(2.dp)
                            .wrapContentSize()
                    )
                }


            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(180.dp)
            ) {
                launch.mission_name?.let {
                    Text(
                        text = it,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(5.dp)
                    )
                }
                launch.launch_site?.site_name_long?.let {
                    Text(
                        text = it,
                        fontSize = 12.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(5.dp)
                    )
                }
            }
            Column(
                modifier = Modifier.padding(top = 5.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                FavButton(model, launch, isFav)
                var stext = ""
                launch.launch_success?.let {
                    if (it)
                        stext = "Success"
                    else
                        stext = "Fail"
                }
                Text(
                    text = stext,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (stext == "Success") Color.Green else Color.Red,
                    modifier = Modifier
                        .padding(5.dp)
                        .padding(top = 5.dp)
                )
            }
        }
    }
}