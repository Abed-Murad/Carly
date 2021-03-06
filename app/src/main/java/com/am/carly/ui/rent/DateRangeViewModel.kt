package com.am.carly.ui.rent

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.am.carly.data.model.Car
import com.am.carly.data.model.Card
import com.am.carly.data.model.Order
import com.am.carly.data.repository.UserRepository
import com.am.carly.ui.maps.ChooseLocationActivity
import com.am.carly.util.ARG_CREATE_NEW_ORDER
import com.am.carly.util.PARM_INTENT_SOURCE
import com.am.carly.util.PARM_ORDER_MODEL
import com.google.firebase.Timestamp
import java.util.*

class DateRangeViewModel(private val userRepository: UserRepository) : ViewModel() {
    lateinit var selectedDate: List<Date>
    private lateinit var mOrder: Order
    lateinit var car: Car

    fun choosePickUpLocationBtnClick(view: View) {
        mOrder =
            Order(
                "",
                "",
                Card("", "", "", ""),
                car,
                "",
                Timestamp(selectedDate.first()),
                Timestamp(selectedDate.last()),
                selectedDate.size,
                false
            )

        view.context.startActivity(Intent(view.context, ChooseLocationActivity::class.java).also {
            it.putExtra(PARM_ORDER_MODEL, mOrder)
            it.putExtra(PARM_INTENT_SOURCE, ARG_CREATE_NEW_ORDER)
        })


    }

}