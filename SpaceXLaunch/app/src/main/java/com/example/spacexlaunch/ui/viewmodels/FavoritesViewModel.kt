package com.example.spacexlaunch.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.aymen.graphql.SearchLaunchQuery
import com.example.spacexlaunch.data.FavoritesDatabase
import com.example.spacexlaunch.data.FavoritesRepository
import com.example.spacexlaunch.data.Launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritesViewModel(application: Application): AndroidViewModel(application) {

    private val _readAllData: LiveData<List<Launch>>
    val readAllData: LiveData<List<Launch>>
        get() = _readAllData
    private val repository: FavoritesRepository

    init {
        val favDao = FavoritesDatabase.getDatabase(application).launchDao()
        repository = FavoritesRepository(favDao)
        _readAllData = repository.readAllData
    }

    fun addFavorite(launch: SearchLaunchQuery.Launch) {
        viewModelScope.launch(Dispatchers.IO) {
            val nlaunch = launch.mission_name?.let {
                launch.details?.let { details ->
                    Launch(
                        it, details,launch.mission_name, launch.launch_date_local as String?,
                        launch.launch_date_unix.toString(),launch.launch_date_utc.toString(),
                        launch.launch_success,launch.launch_site,launch.links,launch.rocket,
                        launch.mission_id as ArrayList<String?>?, launch.ships as ArrayList<SearchLaunchQuery.Ship>?
                    )
                }
            }
            if (nlaunch != null) {
                println("burdaaa")
                repository.addLaunch(nlaunch)
            }
        }
    }

    fun deleteFavorite(launch: SearchLaunchQuery.Launch) {
        viewModelScope.launch(Dispatchers.IO) {
            launch.mission_name?.let { repository.deleteLaunch(it) }
        }
    }

}