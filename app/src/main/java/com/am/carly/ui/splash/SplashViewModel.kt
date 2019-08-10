package com.am.carly.ui.splash

import androidx.lifecycle.ViewModel
import com.am.carly.data.repository.UserRepository

class SplashViewModel(var userRepository: UserRepository) : ViewModel() {
    fun getLoginInfo() = userRepository.getLoginInfo()
}