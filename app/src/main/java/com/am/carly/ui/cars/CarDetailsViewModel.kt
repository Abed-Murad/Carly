package com.am.carly.ui.cars

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.am.carly.data.model.Car
import com.am.carly.data.repository.UserRepository
import com.am.carly.ui.rent.DateRangeActivity
import com.am.carly.util.PARM_CAR_MODEL

class CarDetailsViewModel(var userRepository: UserRepository) : ViewModel() {
    lateinit var car: Car
    fun onNextBtnClick(view: View) {
        view.context.startActivity(Intent(view.context, DateRangeActivity::class.java).also {
            it.putExtra(PARM_CAR_MODEL, car)
        })

    }
}