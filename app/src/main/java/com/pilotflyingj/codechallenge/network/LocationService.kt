package com.pilotflyingj.codechallenge.network

import com.pilotflyingj.codechallenge.network.models.ApiSiteItem
import retrofit2.Response
import retrofit2.http.GET

interface LocationService {
    // full end point: https://raw.githubusercontent.com/PFJCodeChallenge/pfj-locations/master/locations.json
    @GET ("PFJCodeChallenge/pfj-locations/master/locations.json")
    suspend fun getLocations() : Response<ArrayList<ApiSiteItem>>
}