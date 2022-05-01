package com.pilotflyingj.codechallenge.repository

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.model.LatLng
import com.pilotflyingj.codechallenge.network.LocationService
import com.pilotflyingj.codechallenge.repository.models.Site
import timber.log.Timber
import javax.inject.Inject

class MapRepository @Inject constructor(
    private val locationService: LocationService
) {
    //make api call and parse response into Site items
    suspend fun requestLocations(liveDataList: MutableLiveData<MutableList<Site>>){
        val response = locationService.getLocations()
        val requestedSites = response.body()
        val listOfSites = mutableListOf<Site>()

        //check that the api call was successful then create the list of Site items to pass to the viewModel
        if (response.isSuccessful) {
            if (requestedSites != null) {
                for (site in requestedSites){
                    val siteItem = Site(name = site.storeName, location = LatLng(site.latitude, site.longitude))
                    listOfSites.add(siteItem)
                }
                liveDataList.postValue(listOfSites)
            }
        } else {
            //if error occurred during api call log the error
            val error = response.errorBody().toString()
            Timber.d("Clunk $error")
        }
    }
}