package com.am.carly.ui.maps

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.am.carly.R
import com.am.carly.databinding.ActivityChooseLcationBinding
import com.am.carly.ui.base.BaseActivity
import com.am.carly.ui.base.viewmodelfactory.ChooseLocationViewModelFactory
import com.am.carly.ui.maps.MapViewActivity.Companion.GAZA_STRIP_CENTER_LAT_LNG
import com.am.carly.ui.maps.MapViewActivity.Companion.MAP_GAZA_ZOOM_SCALE
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
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
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_choose_lcation)
        mViewModel = ViewModelProviders.of(this, mFactory).get(ChooseLocationViewModel::class.java)
        mBinding.viewModel = mViewModel

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(GAZA_STRIP_CENTER_LAT_LNG, MAP_GAZA_ZOOM_SCALE))
        mMap.setOnMapClickListener { point ->
            mMap.clear()
            mMap.addMarker(MarkerOptions().position(point))
        }

    }

}