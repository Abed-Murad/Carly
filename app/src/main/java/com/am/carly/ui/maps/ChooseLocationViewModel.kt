package com.am.carly.ui.maps

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.am.carly.data.model.Car
import com.am.carly.data.model.Order
import com.am.carly.data.repository.UserRepository
import com.am.carly.ui.policies.PoliciesActivity
import com.am.carly.util.PARM_ORDER_MODEL

class ChooseLocationViewModel(private val userRepository: UserRepository) : ViewModel() {
    lateinit var intentSource: String
    lateinit var order: Order
    lateinit var car: Car

    lateinit var location: String
    fun choosePickUpLocationBtnClick(view: View) {


        order.location = location
        view.context.startActivity(Intent(view.context, PoliciesActivity::class.java).also {
            it.putExtra(PARM_ORDER_MODEL, order)

        })
    }
}