package com.am.carly.ui.cars

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.am.carly.R
import com.am.carly.data.model.Car
import com.am.carly.databinding.ActivityCarDetailsBinding
import com.am.carly.ui.base.BaseActivity
import com.am.carly.ui.maps.MapViewActivity
import com.am.carly.util.PARM_CAR_MODEL
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

//TODO: Replace all the data in the views with a real data from the FireStore Database.
class CarDetailsActivity : BaseActivity(), KodeinAware, OnMapReadyCallback {
    override val kodein by kodein()
    private val mFactory: CarDetailsViewModelFactory by instance()
    private lateinit var mBinding: ActivityCarDetailsBinding
    private lateinit var mViewModel: CarDetailsViewModel
    private lateinit var mMap: GoogleMap
    private lateinit var mCar: Car
    private lateinit var mImagesSliderAdapter: ImagesPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mCar = intent.extras.getParcelable(PARM_CAR_MODEL)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_car_details)
        mViewModel = ViewModelProviders.of(this, mFactory).get(CarDetailsViewModel::class.java)
        mBinding.viewModel = mViewModel
        mBinding.car = mCar
        setupImagesSlider()
    }


    @SuppressLint("SetTextI18n")
    private fun setupImagesSlider() {
        mBinding.imageNumberTextView.text = "1 of ${mCar.imagesList!!.size}"
        mImagesSliderAdapter = ImagesPagerAdapter(this , mCar.imagesList)
        mBinding.imagesPager.adapter = mImagesSliderAdapter
        val imagesCount = mImagesSliderAdapter.count

        mBinding.imagesPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int, positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                mBinding.imageNumberTextView.text = (position + 1).toString() + " of " + imagesCount
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.addMarker(
            MarkerOptions()
                .position(LatLng(31.259279, 34.263260))
                .title("Toyota Yaris")
                .snippet("210 NIS")
        )

        mMap.moveCamera(
            CameraUpdateFactory.newLatLngZoom(
                MapViewActivity.GAZA_STRIP_CENTER_LAT_LNG,
                MapViewActivity.MAP_GAZA_ZOOM_SCALE
            )
        )
    }


}