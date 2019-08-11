package com.am.carly.ui.cars

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.am.carly.R
import com.am.carly.data.model.SliderImage
import com.am.carly.databinding.ActivityCarDetailsBinding
import com.am.carly.ui.base.BaseActivity
import com.am.carly.ui.base.viewmodelfactory.CarDetailsViewModelFactory
import com.am.carly.util.FAKE
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import java.util.*

class CarDetailsActivity : BaseActivity(), KodeinAware {
    override val kodein by kodein()
    private val mFactory: CarDetailsViewModelFactory by instance()
    private lateinit var mBinding: ActivityCarDetailsBinding
    private lateinit var mViewModel: CarDetailsViewModel
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

}