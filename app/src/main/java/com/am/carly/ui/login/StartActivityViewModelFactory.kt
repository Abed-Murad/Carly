package com.am.carly.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.am.carly.data.repository.UserRepository


class StartActivityViewModelFactory constructor(private val userRepository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(StartActivityViewModel::class.java)) {
            StartActivityViewModel(this.userRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}

