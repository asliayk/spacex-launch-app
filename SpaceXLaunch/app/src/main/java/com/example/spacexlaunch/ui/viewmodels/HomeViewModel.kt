package com.example.spacexlaunch.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apollographql.apollo3.exception.ApolloException
import com.aymen.graphql.SearchLaunchQuery
import com.example.spacexlaunch.apollo.ApolloClient
import kotlinx.coroutines.*


class HomeViewModel : ViewModel() {

    private var client = ApolloClient().get()
    private var job: Job? = null

    private lateinit var launchList: ArrayList<SearchLaunchQuery.Launch?>

    private val _lList = MutableLiveData<List<SearchLaunchQuery.Launch?>>()
    val lList: LiveData<List<SearchLaunchQuery.Launch?>>
        get() = _lList


    fun findLaunch(name: String) {
        if(name!="") {
            job = CoroutineScope(Dispatchers.IO).launch {
                val response = try {
                    client.query(SearchLaunchQuery(name)).execute()
                } catch (e: ApolloException) {
                    return@launch
                }
                val launches = response.data?.launches
                if (launches != null && launches.isNotEmpty()) {
                    launchList = ArrayList(launches)
                    _lList.postValue(launches)
                }

            }
        } else {
            _lList.postValue(ArrayList())
        }

    }


}