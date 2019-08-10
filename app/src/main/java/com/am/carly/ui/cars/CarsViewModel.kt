package com.am.carly.ui.cars

import androidx.lifecycle.ViewModel
import com.am.carly.data.repository.UserRepository

class CarsViewModel(var userRepository: UserRepository) : ViewModel() {
    fun getLoginInfo() = userRepository.getLoginInfo()
}