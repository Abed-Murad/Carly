package com.am.carly.ui.cars

import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.am.carly.data.repository.UserRepository
import com.am.carly.ui.maps.ChooseLocationActivity
import com.esafirm.imagepicker.features.ImagePicker

class AddCarViewModel(var userRepository: UserRepository) : ViewModel() {
    fun getLoginInfo() = userRepository.getLoginInfo()

    fun onNextBtnClick(view: View) {
        view.context.startActivity(Intent(view.context, ChooseLocationActivity::class.java))
    }

    fun pickImagesBtnClick(view: View) {
        ImagePicker.create(view.context as AppCompatActivity).start()
    }
}