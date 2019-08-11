package com.am.carly.ui.maps

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.am.carly.data.repository.UserRepository
import com.am.carly.ui.policies.PoliciesActivity

class ChooseLocationViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun choosePickUpLocationBtnClick(view: View) {
        view.context.startActivity(Intent(view.context , PoliciesActivity::class.java))
    }
}