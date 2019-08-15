package com.am.carly.ui.cars

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.am.carly.data.repository.UserRepository


class CarDetailsViewModelFactory constructor(private val userRepository: UserRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CarDetailsViewModel::class.java)) {
            CarDetailsViewModel(this.userRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}

