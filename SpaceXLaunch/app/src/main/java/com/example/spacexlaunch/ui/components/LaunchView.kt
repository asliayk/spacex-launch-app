package com.example.spacexlaunch.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.example.spacexlaunch.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.aymen.graphql.SearchLaunchQuery
import com.example.spacexlaunch.data.Launch
import com.example.spacexlaunch.ui.viewmodels.FavoritesViewModel
import com.example.spacexlaunch.ui.viewmodels.HomeViewModel

@OptIn(ExperimentalCoilApi::class)
@Composable
fun LaunchView(
    model: HomeViewModel,
    favModel: FavoritesViewModel,
    navController: NavHostController,
    name: String
) {
    var upName = name
    if (name.contains("99"))
        upName = name.replace("99", "/")
    var slinks = arrayListOf<String>()
    var date = ""

    val slaunch = model.lList.value?.find { it?.mission_name == upName }
    var launch: Launch?
    if (slaunch != null) {
        launch = slaunch.id?.let {
            Launch(
                it,
                slaunch.details,
                slaunch.mission_name,
                slaunch.launch_date_local as String?,
                null,
                null,
                slaunch.launch_success,
                slaunch.launch_site,
                slaunch.links,
                slaunch.rocket,
                slaunch.mission_id as ArrayList<String?>?,
                slaunch.ships as ArrayList<SearchLaunchQuery.Ship>?
            )
        }
    } else {
        val favLaunch = favModel.readAllData.value?.find { it.missionName == upName }
        launch = favLaunch
    }
    var imageLink = ""
    if (launch?.links != null && launch.links?.flickr_images != null && launch.links!!.flickr_images!!.isNotEmpty())
        imageLink = launch.links!!.flickr_images?.get(0).toString()
    if (launch != null) {
        if (launch.links != null) {
            launch.links!!.article_link?.let { slinks.add(it) }
            launch.links!!.video_link?.let { slinks.add(it) }
            launch.links!!.wikipedia?.let { slinks.add(it) }
        }
        if (launch.launchDateLocal != null)
            date = launch.launchDateLocal.toString()
    }

    Scaffold(topBar = {
        TopAppBar(backgroundColor = Color.Black, title = {
            if (launch != null) {
                launch.missionName?.let { Text(text = it, color = Color.White) }
            }
        }, navigationIcon = if (navController.previousBackStackEntry != null) {
            {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            }
        } else {
            null
        })
    }, modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.spaceblue),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Scaffold(backgroundColor = Color.Transparent) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp).verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (launch != null) {
                    val success: String = if (launch.launchSuccess == true)
                        "A Successful Launch in "
                    else
                        "An Unsuccessful Launch in "
                    launch.launchSite?.site_name_long?.let {

                        Text(
                            text = success + it,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            style = MaterialTheme.typography.body2,
                            modifier = Modifier.padding(start = 15.dp, end = 15.dp)
                        )
                    }
                }
                if (date != "") {
                    var dlist = date.split("T")
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("Launch Date: ")
                            }
                            append(dlist[0] + " " + dlist[1])
                        },
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        style = MaterialTheme.typography.h1,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(top = 15.dp, start = 15.dp, end = 15.dp)
                    )
                }
                if (imageLink != "") {
                    Image(
                        painter = rememberImagePainter(imageLink),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(170.dp)
                            .fillMaxSize()
                            .padding(17.dp)
                            .border(2.dp, Color.White),

                        )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.rocimg),
                        contentDescription = null,
                        modifier = Modifier.size(170.dp)
                            .fillMaxSize()
                            .padding(17.dp)
                            .border(2.dp, Color.White),
                        contentScale = ContentScale.FillBounds
                    )
                }
                if (launch != null) {
                    launch.rocket?.rocket_name?.let {
                        Text(
                            text = buildAnnotatedString {
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                    append("Rocket Name: ")
                                }
                                append(it)
                            }, fontSize = 12.sp,
                            style = MaterialTheme.typography.h1,
                            color = Color.White
                        )
                    }
                    launch.rocket?.rocket_type?.let {
                        Text(
                            text = buildAnnotatedString {
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                    append("Rocket Type: ")
                                }
                                append(it)
                            }, fontSize = 12.sp,
                            style = MaterialTheme.typography.h1,
                            color = Color.White
                        )
                    }
                    launch.rocket?.rocket?.country?.let {
                        Text(
                            text = buildAnnotatedString {
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                    append("Rocket Country: ")
                                }
                                append(it)
                            }, fontSize = 12.sp,
                            style = MaterialTheme.typography.h1,
                            color = Color.White
                        )
                    }
                    launch.rocket?.rocket?.active?.let {
                        Text(
                            text = buildAnnotatedString {
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                    append("Rocket Status: ")
                                }
                                if (it)
                                    append("Active")
                                else
                                    append("Not Active")
                            },
                            fontSize = 12.sp,
                            style = MaterialTheme.typography.h1,
                            color = Color.White
                        )
                    }
                    launch.details?.let {
                        Text(
                            text = it,
                            fontSize = 11.sp,
                            modifier = Modifier.padding(15.dp),
                            style = MaterialTheme.typography.h1,
                            color = Color.White
                        )
                    }
                    launch.rocket?.rocket?.description?.let {
                        Text(
                            text = it,
                            fontSize = 11.sp,
                            modifier = Modifier.padding(15.dp),
                            style = MaterialTheme.typography.h1,
                            color = Color.White
                        )
                    }
                }


                Column(modifier = Modifier.padding(top = 10.dp, start = 15.dp)) {
                    Text(
                        text = "More Information", fontSize = 15.sp, fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 7.dp),
                        style = MaterialTheme.typography.h1,
                        color = Color.White
                    )
                    slinks.forEach {
                        val uriHandler = LocalUriHandler.current
                        val annotatedLinkString: AnnotatedString = buildAnnotatedString {

                            val startIndex = 0
                            val endIndex = it.length
                            append(it)
                            addStyle(
                                style = SpanStyle(
                                    fontSize = 10.sp,
                                    color = Color.White,
                                    textDecoration = TextDecoration.Underline
                                ), start = startIndex, end = endIndex
                            )

                            addStringAnnotation(
                                tag = "URL",
                                annotation = it,
                                start = 0,
                                end = it.length
                            )

                        }

                        ClickableText(
                            modifier = Modifier
                                .padding(bottom = 3.dp)
                                .fillMaxWidth(),
                            text = annotatedLinkString,
                            onClick = {
                                annotatedLinkString
                                    .getStringAnnotations("URL", it, it)
                                    .firstOrNull()?.let { stringAnnotation ->
                                        uriHandler.openUri(stringAnnotation.item)
                                    }
                            }
                        )

                    }
                }
            }
        }
    }
}