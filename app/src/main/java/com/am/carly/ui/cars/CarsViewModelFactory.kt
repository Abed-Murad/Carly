package com.am.carly.ui.cars

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.am.carly.data.repository.UserRepository

class CarsViewModelFactory constructor(private val userRepository: UserRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CarsViewModel::class.java)) {
            CarsViewModel(this.userRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}

