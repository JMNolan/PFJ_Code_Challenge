package com.pilotflyingj.codechallenge.repository

import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.model.LatLng
import com.pilotflyingj.codechallenge.network.LocationService
import com.pilotflyingj.codechallenge.network.models.ApiSiteItem
import com.pilotflyingj.codechallenge.repository.models.Site
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class MapRepository @Inject constructor(
    private val locationService: LocationService
) {
    //make api call and return the response
    suspend fun requestLocations() : Response<List<ApiSiteItem>> {
        return locationService.getLocations()
    }
}
