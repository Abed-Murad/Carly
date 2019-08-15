package com.am.carly.ui.cars

import android.content.Intent
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.am.carly.data.repository.UserRepository
import com.am.carly.ui.maps.MapViewActivity

class CarsViewModel(var userRepository: UserRepository) : ViewModel() {

    fun onMapViewBtnClick(view: View) {
        view.context.startActivity(Intent(view.context, MapViewActivity::class.java))
    }
}