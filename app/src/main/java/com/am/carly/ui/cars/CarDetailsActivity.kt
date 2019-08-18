package com.am.carly.ui.cars

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.am.carly.data.model.Car
import com.am.carly.data.model.User
import com.am.carly.databinding.ActivityCarDetailsBinding
import com.am.carly.ui.base.BaseActivity
import com.am.carly.util.PARM_CAR_MODEL
import com.am.carly.util.getCarMarker
import com.bumptech.glide.Glide
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.Timestamp
import kotlinx.android.synthetic.main.card_owner_info.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import org.ocpsoft.prettytime.PrettyTime


//TODO: Replace all the data in the views with a real data from the FireStore Database.
class CarDetailsActivity : BaseActivity(), KodeinAware {
    override val kodein by kodein()
    private val mFactory: CarDetailsViewModelFactory by instance()
    private lateinit var mBinding: ActivityCarDetailsBinding
    private lateinit var mViewModel: CarDetailsViewModel
    private lateinit var mCar: Car
    private lateinit var mImagesSliderAdapter: ImagesPagerAdapter
    private var mGoogleMap: GoogleMap? = null
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mCar = intent.extras.getParcelable(PARM_CAR_MODEL)

        mBinding = DataBindingUtil.setContentView(this, com.am.carly.R.layout.activity_car_details)
        mViewModel = ViewModelProviders.of(this, mFactory).get(CarDetailsViewModel::class.java)
        mBinding.viewModel = mViewModel
        mBinding.car = mCar
        mBinding.owner =
            User("22311", "Abed", "asdaklsd@laskd.com", "https://picsum.photos/400/300", Timestamp.now(), 8, true)
        setupImagesSlider()
        setupMapFragment()

        Glide.with(this).load("https://picsum.photos/400/300").into(profile_image)
        joinedDateTextView.text = "Joined ${PrettyTime().format(Timestamp.now().toDate())}"

    }

    private fun setupMapFragment() {
        val mapFragment = supportFragmentManager.findFragmentById(com.am.carly.R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync { googleMap ->
            mGoogleMap = googleMap

            val carMarker = getCarMarker(this)
            mGoogleMap!!.addMarker(
                MarkerOptions()
                    .position(LatLng(31.259279, 34.263260))
                    .title("Toyota Yaris")
                    .icon(BitmapDescriptorFactory.fromBitmap(carMarker))
                    .snippet("210 NIS")
            )
            moveToCurrentLocation(LatLng(31.259279, 34.263260))
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setupImagesSlider() {
        mBinding.imageNumberTextView.text = "1 of ${mCar.imagesList!!.size}"
        mImagesSliderAdapter = ImagesPagerAdapter(this, mCar.imagesList)
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


    fun moveToCurrentLocation(currentLocation: LatLng) {
        mGoogleMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15f))
        // Zoom in, animating the camera.
        mGoogleMap!!.animateCamera(CameraUpdateFactory.zoomIn())
        // Zoom out to zoom level 10, animating with a duration of 2 seconds.
        mGoogleMap!!.animateCamera(CameraUpdateFactory.zoomTo(15f), 2000, null)


    }

}

