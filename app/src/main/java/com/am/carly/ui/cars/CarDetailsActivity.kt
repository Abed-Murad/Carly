package com.am.carly.ui.cars

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.am.carly.R
import com.am.carly.data.model.SliderImage
import com.am.carly.databinding.ActivityCarDetailsBinding
import com.am.carly.ui.base.BaseActivity
import com.am.carly.ui.maps.MapViewActivity
import com.am.carly.util.FAKE
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import java.util.*

class CarDetailsActivity : BaseActivity(), KodeinAware, OnMapReadyCallback {
    override val kodein by kodein()
    private val mFactory: CarDetailsViewModelFactory by instance()
    private lateinit var mBinding: ActivityCarDetailsBinding
    private lateinit var mViewModel: CarDetailsViewModel
    private lateinit var mMap: GoogleMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_car_details)
        mViewModel = ViewModelProviders.of(this, mFactory).get(CarDetailsViewModel::class.java)
        mBinding.viewModel = mViewModel
        setupImagesPager()
    }


    private fun setupImagesPager() {
        val imagesPagerAdapter = ImagesPagerAdapter(this, getFakeSliderImagesList())
        mBinding.imagesPager.adapter = imagesPagerAdapter
        val imagesCount = imagesPagerAdapter.count

        mBinding.imageNumberTextView.text = "1 of $imagesCount"

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


    fun getFakeSliderImagesList(): ArrayList<SliderImage> {
        val imagesList = ArrayList<SliderImage>()
        imagesList.add(
            SliderImage(
                "Football Field",
                FAKE.IMG_URL_FOOTBALL
            )
        )
        imagesList.add(
            SliderImage(
                "Stars At Night",
                FAKE.IMG_URL_STARS
            )
        )
        imagesList.add(
            SliderImage(
                "Best Band Ever",
                FAKE.IMG_URL_STARS
            )
        )
        return imagesList
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