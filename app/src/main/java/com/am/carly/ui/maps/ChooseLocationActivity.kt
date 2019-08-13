package com.am.carly.ui.maps

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.am.carly.databinding.ActivityChooseLcationBinding
import com.am.carly.ui.base.BaseActivity
import com.am.carly.ui.base.viewmodelfactory.ChooseLocationViewModelFactory
import com.am.carly.ui.maps.MapViewActivity.Companion.GAZA_STRIP_CENTER_LAT_LNG
import com.am.carly.ui.maps.MapViewActivity.Companion.MAP_GAZA_ZOOM_SCALE
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


class ChooseLocationActivity : BaseActivity(), KodeinAware, OnMapReadyCallback {

    override val kodein by kodein()
    private val mFactory: ChooseLocationViewModelFactory by instance()
    private lateinit var mBinding: ActivityChooseLcationBinding
    private lateinit var mViewModel: ChooseLocationViewModel
    private lateinit var mMap: GoogleMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, com.am.carly.R.layout.activity_choose_lcation)
        mViewModel = ViewModelProviders.of(this, mFactory).get(ChooseLocationViewModel::class.java)
        mBinding.viewModel = mViewModel

        val mapFragment = supportFragmentManager.findFragmentById(com.am.carly.R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 1001)
        } else {
            mMap.isMyLocationEnabled = true
        }


        mMap.setOnMyLocationButtonClickListener {
            mMap.clear()
            mMap.addMarker(MarkerOptions().position(LatLng(mMap.myLocation.latitude, mMap.myLocation.longitude)))
            true
        }


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(GAZA_STRIP_CENTER_LAT_LNG, MAP_GAZA_ZOOM_SCALE))
        mMap.setOnMapClickListener { point ->
            mMap.clear()
            mMap.addMarker(MarkerOptions().position(point))
        }

    }


    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 1001) {
            mMap.isMyLocationEnabled = true
        }
    }


}