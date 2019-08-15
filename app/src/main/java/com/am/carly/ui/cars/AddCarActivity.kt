package com.am.carly.ui.cars

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.am.carly.databinding.ActivityAddCarBinding
import com.am.carly.ui.base.BaseActivity
import com.am.carly.ui.login.AddCarViewModelFactory
import com.esafirm.imagepicker.features.ImagePicker
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


class AddCarActivity : BaseActivity(), KodeinAware {
    override val kodein by kodein()
    private val mFactory: AddCarViewModelFactory by instance()
    private lateinit var mBinding: ActivityAddCarBinding
    private lateinit var mViewModel: AddCarViewModel
    private lateinit var mImagesPagerAdapter: AddCarImagesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, com.am.carly.R.layout.activity_add_car)
        mViewModel = ViewModelProviders.of(this, mFactory).get(AddCarViewModel::class.java)
        mBinding.viewModel = mViewModel
        setupImagesPager()
    }


    private fun setupImagesPager() {
        mImagesPagerAdapter = AddCarImagesAdapter(this)
        mBinding.imagesPager.adapter = mImagesPagerAdapter
        val imagesCount = mImagesPagerAdapter.count


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


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {

            val images = ImagePicker.getImages(data)
            mBinding.imageNumberTextView.text = "1 of ${images.size}"

            mImagesPagerAdapter.updateImagesList(images)
        }
        super.onActivityResult(requestCode, resultCode, data)

    }

}
