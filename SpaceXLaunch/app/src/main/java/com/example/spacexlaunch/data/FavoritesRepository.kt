package com.example.spacexlaunch.data

import androidx.lifecycle.LiveData

class FavoritesRepository(private val launchDao: LaunchDao) {

    val readAllData: LiveData<List<Launch>> = launchDao.readAllData()

    suspend fun addLaunch(launch: Launch) {
        launchDao.addLaunch(launch)
    }

    suspend fun deleteLaunch(name: String) {
        launchDao.deleteLaunch(name)
    }
}