package com.example.spacexlaunch.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface LaunchDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addLaunch(launch: Launch)

    @Query("DELETE FROM favorites_table WHERE missionName = :mission_name")
    suspend fun deleteLaunch(mission_name: String)

    @Query("SELECT * FROM favorites_table")
    fun readAllData(): LiveData<List<Launch>>

}