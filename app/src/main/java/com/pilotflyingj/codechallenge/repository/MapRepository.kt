package com.pilotflyingj.codechallenge.repository

import com.pilotflyingj.codechallenge.network.LocationService
import com.pilotflyingj.codechallenge.network.models.ApiSiteItem
import retrofit2.Response
import javax.inject.Inject

class MapRepository @Inject constructor(
    private val locationService: LocationService
) {
    //make api call and return the response
    suspend fun requestLocations() : Response<List<ApiSiteItem>> {
        return locationService.getLocations()
    }
}
