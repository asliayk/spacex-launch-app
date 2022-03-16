package com.example.spacexlaunch.data

import androidx.room.TypeConverter
import com.aymen.graphql.SearchLaunchQuery
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromLaunchSitetoString(launchSite: SearchLaunchQuery.Launch_site): String? {
        return Gson().toJson(launchSite)
    }

    @TypeConverter
    fun fromLinkstoString(links: SearchLaunchQuery.Links): String? {
        return Gson().toJson(links)
    }

    @TypeConverter
    fun fromShiptoString(ship: ArrayList<SearchLaunchQuery.Ship>): String? {
        return Gson().toJson(ship)
    }

    @TypeConverter
    fun fromMissionIdtoString(mId: ArrayList<String>): String? {
        return Gson().toJson(mId)
    }

    @TypeConverter
    fun fromRockettoString(rocket: SearchLaunchQuery.Rocket): String? {
        return Gson().toJson(rocket)
    }

    @TypeConverter
    fun fromStringToLaunchSite(launchSite: String): SearchLaunchQuery.Launch_site? {
        return Gson().fromJson(launchSite,SearchLaunchQuery.Launch_site::class.java)
    }

    @TypeConverter
    fun fromStringToLinks(links: String): SearchLaunchQuery.Links? {
        return Gson().fromJson(links,SearchLaunchQuery.Links::class.java)
    }

    @TypeConverter
    fun fromStringToRocket(rocket: String): SearchLaunchQuery.Rocket? {
        return Gson().fromJson(rocket,SearchLaunchQuery.Rocket::class.java)
    }

    @TypeConverter
    fun fromStringToShip(ship: String): ArrayList<SearchLaunchQuery.Ship>? {
        val listType = object : TypeToken<List<SearchLaunchQuery.Ship>>() {}.type
        return Gson().fromJson(ship,listType)
    }

    @TypeConverter
    fun fromStringToMissionId(mId: String): ArrayList<String>? {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(mId,listType)
    }
}