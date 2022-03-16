package com.example.spacexlaunch.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aymen.graphql.SearchLaunchQuery

@Entity(tableName = "favorites_table")
data class Launch(
    @PrimaryKey
    var id: String,
    var details: String?,
    var missionName: String?,
    var launchDateLocal: String? = null,
    var launchDateUnix: String? = null,
    var launchDateUtc: String? = null,
    var launchSuccess: Boolean? = false,
    var launchSite: SearchLaunchQuery.Launch_site? = null,
    var links: SearchLaunchQuery.Links? = null,
    var rocket: SearchLaunchQuery.Rocket? = null,
    var mission_id: ArrayList<String?>? = null,
    var ships: ArrayList<SearchLaunchQuery.Ship>? = null

)