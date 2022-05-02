package com.pilotflyingj.codechallenge.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
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

    //call api request function on background thread and load it into the data source for the map pins
    fun loadLiveDataIntoList() {
        Timber.d("The network call has been made")
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = mapRepository.requestLocations()
                val requestedSites = response.body()
                val listOfSites = mutableListOf<Site>()

                //check that the api call was successful then create the list of Site items to pass to the viewModel
                if (response.isSuccessful) {
                    if (requestedSites != null) {
                        for (site in requestedSites) {
                            val siteItem = Site(
                                name = site.storeName,
                                location = LatLng(site.latitude, site.longitude)
                            )
                            listOfSites.add(siteItem)
                        }
                        locationLiveDataList.postValue(listOfSites)
                    }
                } else {
                    //if error occurred during api call log the error
                    val error = response.errorBody().toString()
                    Timber.d("Clunk $error")
                }
            }
        }
    }
}