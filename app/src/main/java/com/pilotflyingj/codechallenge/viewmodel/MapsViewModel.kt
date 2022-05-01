package com.pilotflyingj.codechallenge.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pilotflyingj.codechallenge.repository.MapRepository
import com.pilotflyingj.codechallenge.repository.models.Site
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class MapsViewModel @ViewModelInject constructor(
    private val mapRepository: MapRepository
) : ViewModel() {
    var locationLiveDataList: MutableLiveData<MutableList<Site>> = MutableLiveData()
    lateinit var errorMessage: String

    //call api request function on background thread and load it into the data source for the map pins
    fun loadLiveDataIntoList() {
        Timber.d("The network call has been made")
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                mapRepository.requestLocations(locationLiveDataList)
            }
        }
    }
}