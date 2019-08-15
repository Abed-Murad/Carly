package com.am.carly.ui.rent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.am.carly.data.repository.UserRepository


class DateRangeViewModelFactory constructor(private val userRepository: UserRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(DateRangeViewModel::class.java)) {
            DateRangeViewModel(this.userRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}

