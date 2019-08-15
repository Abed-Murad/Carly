package com.am.carly.ui.cities

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.am.carly.data.repository.UserRepository
import com.am.carly.ui.cars.AddCarActivity

class CitiesViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun onLogoutBtnClick(view: View) {
        view.context.startActivity(Intent(view.context, AddCarActivity::class.java))
    }
}