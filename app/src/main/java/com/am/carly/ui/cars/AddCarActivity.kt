package com.am.carly.ui.cars

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.am.carly.databinding.ActivityAddCarBinding
import com.am.carly.ui.base.BaseActivity
import com.am.carly.ui.login.AddCarViewModelFactory
import com.esafirm.imagepicker.features.ImagePicker
import kotlinx.android.synthetic.main.activity_add_car.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


class AddCarActivity : BaseActivity(), KodeinAware {
    override val kodein by kodein()
    private val mFactory: AddCarViewModelFactory by instance()
    private lateinit var mBinding: ActivityAddCarBinding
    private lateinit var mViewModel: AddCarViewModel
    private lateinit var mImagesSliderAdapter: ImagesSliderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, com.am.carly.R.layout.activity_add_car)
        mViewModel = ViewModelProviders.of(this, mFactory).get(AddCarViewModel::class.java)
        mBinding.viewModel = mViewModel
        setupImagesSlider()
    }

    @SuppressLint("SetTextI18n")
    private fun setupImagesSlider() {
        mImagesSliderAdapter = ImagesSliderAdapter(this)
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

    @SuppressLint("SetTextI18n")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            val images = ImagePicker.getImages(data)
            mBinding.imageNumberTextView.text = "1 of ${images.size}"
            mImagesSliderAdapter.updateImagesList(images)

            if (images != null && images.size != 0) {
                imagesHolder.visibility = GONE
                imageNumberTextView.visibility = VISIBLE
            } else {
                imagesHolder.visibility = VISIBLE
                imageNumberTextView.visibility = GONE
            }
        }
        super.onActivityResult(requestCode, resultCode, data)

    }

}
