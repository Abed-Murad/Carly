package com.am.carly.ui.payment

import android.view.View
import androidx.lifecycle.ViewModel
import com.am.carly.data.model.Order
import com.am.carly.data.repository.UserRepository

class PaymentActivityViewModel (private val userRepository: UserRepository) : ViewModel() {
    lateinit var order: Order

    var cardNumber: String = ""
    var expire: String = ""
    var cvv: String = ""
    var nameOnCard: String = ""

    fun nextBtnClick(view: View) {

    }
}