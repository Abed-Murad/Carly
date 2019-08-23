package com.am.carly.ui.policies

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.am.carly.data.model.Order
import com.am.carly.data.repository.UserRepository
import com.am.carly.ui.payment.PaymentActivity
import com.am.carly.util.PARM_ORDER_MODEL

class PoliciesViewModel(private val userRepository: UserRepository) : ViewModel() {

    lateinit var order: Order

    fun onNextBtnClick(view: View) {

        view.context.startActivity(Intent(view.context, PaymentActivity::class.java).also {
            it.putExtra(PARM_ORDER_MODEL, order)

        })
    }
}