package com.am.carly.ui.policies

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.am.carly.data.model.Car
import com.am.carly.data.model.Order
import com.am.carly.data.repository.UserRepository
import com.am.carly.ui.cities.CitiesActivity
import com.am.carly.ui.payment.PaymentActivity
import com.am.carly.util.ARG_ADD_CAR_ACTIVITY
import com.am.carly.util.PARM_ORDER_MODEL

class PoliciesViewModel(private val userRepository: UserRepository) : ViewModel() {
    lateinit var intentSource: String
    lateinit var order: Order
    lateinit var car: Car

    fun onNextBtnClick(view: View) {

        if (intentSource == ARG_ADD_CAR_ACTIVITY) {
            view.context.startActivity(Intent(view.context, CitiesActivity::class.java).also {
            })

        } else {
            view.context.startActivity(Intent(view.context, PaymentActivity::class.java).also {
                it.putExtra(PARM_ORDER_MODEL, order)
            })

        }


    }
}