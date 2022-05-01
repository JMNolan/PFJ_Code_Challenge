package com.pilotflyingj.codechallenge.repository.models

import com.google.android.gms.maps.model.LatLng

data class Site(
    //data class that will be used to create pins on the map
    val name: String,
    val location: LatLng
)