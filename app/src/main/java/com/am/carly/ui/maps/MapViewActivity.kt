package com.am.carly.ui.maps

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.am.carly.R
import com.am.carly.databinding.ActivityMapViewBinding
import com.am.carly.ui.base.BaseActivity
import com.am.carly.ui.base.viewmodelfactory.MapViewViewModelFactory
import com.am.carly.ui.cars.CarDetailsActivity
import com.am.carly.util.FAKE
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class MapViewActivity : BaseActivity(), KodeinAware {
    override val kodein by kodein()
    private val mFactory: MapViewViewModelFactory by instance()
    private lateinit var mBinding: ActivityMapViewBinding
    private lateinit var mViewModel: MapViewViewModel
    private var mGoogleMap: GoogleMap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_map_view)
        mViewModel = ViewModelProviders.of(this, mFactory).get(MapViewViewModel::class.java)
        setupMapFragment()
    }


    private fun setupMapFragment() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(object : OnMapReadyCallback {
            override fun onMapReady(googlMap: GoogleMap?) {
                mGoogleMap = googlMap

                mGoogleMap!!.setOnInfoWindowClickListener {
                    startActivity(Intent(this@MapViewActivity, CarDetailsActivity::class.java))
                }

                loadMapFakeData()
                mGoogleMap!!.moveCamera(
                    CameraUpdateFactory.newLatLngZoom(
                        PALESTINE_CENTER_LAT_LNG, MAP_PALESTINE_ZOOM_SCALE
                    )
                )

            }

            private fun loadMapFakeData() {
                val fakeGazaCarsList = FAKE.getGazaFakeMarkersList(this@MapViewActivity)
                val fakeWestBankCarsList = FAKE.getWestBankFakeMarkersList(this@MapViewActivity)
                for (markerOptions in fakeGazaCarsList)
                    mGoogleMap!!.addMarker(markerOptions)
                for (markerOptions in fakeWestBankCarsList)
                    mGoogleMap!!.addMarker(markerOptions)
            }
        })
    }

    val MAP_GAZA_ZOOM_SCALE = 10.4f
    val MAP_PALESTINE_ZOOM_SCALE = 7.5f
    val GAZA_STRIP_CENTER_LAT_LNG = LatLng(31.442249, 34.396284)
    val PALESTINE_CENTER_LAT_LNG = LatLng(31.460364, 34.972938)

}