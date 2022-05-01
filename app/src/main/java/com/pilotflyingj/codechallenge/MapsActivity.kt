package com.pilotflyingj.codechallenge

import android.content.DialogInterface
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.pilotflyingj.codechallenge.repository.models.Site
import com.pilotflyingj.codechallenge.viewmodel.MapsViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    private val viewModel: MapsViewModel by viewModels()
    private lateinit var siteList: MutableList<Site>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        (supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment)?.getMapAsync(this)

        //check to see if the data list has been populated in the viewModel and execute the api call to populate it if not
        if (viewModel.locationLiveDataList.value == null){
            viewModel.loadLiveDataIntoList()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        //set starting camera position for mapView
        val usaCenter = LatLng(39.510453, -96.52356)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(usaCenter, 2F))

        //update mapView with markers when live data list from the viewModel updates
        viewModel.locationLiveDataList.observe(this){
            viewModel.locationLiveDataList.value?.let {
                siteList = it
                for (site in siteList) {
                    googleMap.addMarker(
                        MarkerOptions()
                            .position(site.location)
                            .title(site.name)
                    )
                }
            }
        }
    }
}