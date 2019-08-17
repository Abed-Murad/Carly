package com.am.carly.ui.cities

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.am.carly.data.repository.UserRepository
import com.am.carly.ui.login.StartActivity
import com.google.firebase.auth.FirebaseAuth

class CitiesViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun onLogoutBtnClick(view: View) {
        FirebaseAuth.getInstance().signOut()
        view.context.startActivity(Intent(view.context, StartActivity::class.java))
    }
}