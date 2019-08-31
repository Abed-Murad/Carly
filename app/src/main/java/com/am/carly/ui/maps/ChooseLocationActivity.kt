package com.am.carly.ui.maps

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.LocationManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.am.carly.data.model.Car
import com.am.carly.data.model.Order
import com.am.carly.databinding.ActivityChooseLcationBinding
import com.am.carly.ui.base.BaseActivity
import com.am.carly.ui.maps.MapViewActivity.Companion.GAZA_STRIP_CENTER_LAT_LNG
import com.am.carly.ui.maps.MapViewActivity.Companion.MAP_GAZA_ZOOM_SCALE
import com.am.carly.util.ARG_ADD_CAR_ACTIVITY
import com.am.carly.util.PARM_CAR_MODEL
import com.am.carly.util.PARM_INTENT_SOURCE
import com.am.carly.util.PARM_ORDER_MODEL
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
    private lateinit var mOrder: Order
    private lateinit var mCar: Car
    private lateinit var intentSource: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(this, mFactory).get(ChooseLocationViewModel::class.java)
        intentSource = intent.extras.getString(PARM_INTENT_SOURCE)
        mViewModel.intentSource = intentSource
        if (intentSource == ARG_ADD_CAR_ACTIVITY) {
            mCar = intent.extras.getParcelable(PARM_CAR_MODEL)
            mViewModel.car = mCar
        } else {
            mOrder = intent.extras.getParcelable(PARM_ORDER_MODEL)
            mViewModel.order = mOrder
        }
        mBinding = DataBindingUtil.setContentView(this, com.am.carly.R.layout.activity_choose_lcation)
        mViewModel.intentSource = intentSource
        mBinding.viewModel = mViewModel

        val mapFragment =
            supportFragmentManager.findFragmentById(com.am.carly.R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                1001
            )
        } else {
            mMap.isMyLocationEnabled = true

        }


        mMap.setOnMyLocationButtonClickListener {
            mMap.clear()
            val service = getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val criteria = Criteria()
            val provider = service.getBestProvider(criteria, false)
            val location = service.getLastKnownLocation(provider)
            val userLocation = LatLng(location.latitude, location.longitude)
            mMap.addMarker(MarkerOptions().position(userLocation))
            mViewModel.location = userLocation.toString()
            true
        }

        mMap.moveCamera(
            CameraUpdateFactory.newLatLngZoom(
                GAZA_STRIP_CENTER_LAT_LNG,
                MAP_GAZA_ZOOM_SCALE
            )
        )
        mMap.setOnMapClickListener { point ->
            mMap.clear()
            mMap.addMarker(MarkerOptions().position(point))
            mViewModel.location = point.toString()
        }

    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 1001) {
            mMap.isMyLocationEnabled = true
        }
    }

}