package com.am.carly.ui.maps

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.am.carly.data.model.Car
import com.am.carly.data.model.Order
import com.am.carly.data.repository.UserRepository
import com.am.carly.ui.policies.PoliciesActivity
import com.am.carly.util.*

class ChooseLocationViewModel(private val userRepository: UserRepository) : ViewModel() {
    lateinit var intentSource: String
     var order: Order  = Order("" , "" , null , null ,"" , null  , null , 0 , false)
    lateinit var car: Car
    lateinit var location: String

    fun choosePickUpLocationBtnClick(view: View) {
        order.location = location
        view.context.startActivity(Intent(view.context, PoliciesActivity::class.java).also {
            if (intentSource == ARG_ADD_CAR_ACTIVITY) {
                it.putExtra(PARM_CAR_MODEL, car)
                it.putExtra(PARM_INTENT_SOURCE, ARG_ADD_CAR_ACTIVITY)
            } else {
                it.putExtra(PARM_ORDER_MODEL, order)
                it.putExtra(PARM_INTENT_SOURCE, ARG_CREATE_NEW_ORDER)
            }
        })
    }
}