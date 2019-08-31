package com.am.carly.ui.cars

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.am.carly.databinding.ActivityAddCarBinding
import com.am.carly.ui.base.BaseActivity
import com.am.carly.ui.login.AddCarViewModelFactory
import com.esafirm.imagepicker.features.ImagePicker
import com.esafirm.imagepicker.model.Image
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.label.FirebaseVisionOnDeviceImageLabelerOptions
import kotlinx.android.synthetic.main.activity_add_car.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import java.io.File


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

            if (images != null && images.size != 0) {
                imagesHolder.visibility = GONE
                imageNumberTextView.visibility = VISIBLE
                images.forEach {
                    checkIfCarIncludesACarImage(it)
                }
                mImagesSliderAdapter.updateImagesList(images)
            } else {
                imagesHolder.visibility = VISIBLE
                imageNumberTextView.visibility = GONE
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun checkIfCarIncludesACarImage(image: Image) {
        //Configure the detector//
        val options = FirebaseVisionOnDeviceImageLabelerOptions.Builder()
            .setConfidenceThreshold(0.7f)
            .build()

        //Create a FirebaseVisionImage object//

        val firebaseVisionImage =
            FirebaseVisionImage.fromFilePath(this, Uri.fromFile(File(image.path)))

        //Create an instance of FirebaseVisionLabelDetector//

        val detector = FirebaseVision.getInstance().getOnDeviceImageLabeler(options)

        //Register an OnSuccessListener//
        detector.processImage(firebaseVisionImage).addOnSuccessListener { labels ->


            var isCar = false
            labels.forEach {
                val text = it.text
                val entityId = it.entityId
                val confidence = it.confidence
                Log.d("ttt", "text:$text entityId:$entityId confidence:$confidence")

                if (it.text == "Vehicle" || it.text == "Car") {
                    isCar = true
                }
            }
            if (!isCar) {
                onSNACK(mBinding.priceLayout)
                imagesHolder.visibility = VISIBLE
                imageNumberTextView.visibility = GONE
            }
        }.addOnFailureListener { e ->
            Log.e("tt", e.message)
        }
    }


    fun onSNACK(view: View) {
        //Snackbar(view)
        val snackbar = Snackbar.make(
            view, "This is not a car image, try again!",
            Snackbar.LENGTH_LONG
        ).setAction("Close", null)
        snackbar.show()
    }

}
