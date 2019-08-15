package com.am.carly.ui.cars

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.am.carly.data.repository.UserRepository
import com.am.carly.ui.rent.DateRangeActivity

class CarDetailsViewModel(var userRepository: UserRepository) : ViewModel() {

    fun onNextBtnClick(view: View) {
        view.context.startActivity(Intent(view.context, DateRangeActivity::class.java))
    }
}