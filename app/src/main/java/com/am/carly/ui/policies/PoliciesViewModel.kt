package com.am.carly.ui.policies

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.am.carly.data.repository.UserRepository
import com.am.carly.ui.payment.PaymentActivity

class PoliciesViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun onNextBtnClick(view: View) {
        view.context.startActivity(Intent(view.context, PaymentActivity::class.java))
    }
}