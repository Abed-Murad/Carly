package com.am.carly.ui.payment

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.am.carly.data.repository.UserRepository
import com.am.carly.ui.cities.CitiesActivity

class PaymentSuccessActivityViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun onDoneBtnClick(view: View) {
        view.context.startActivity(Intent(view.context, CitiesActivity::class.java))
    }
}