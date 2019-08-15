package com.am.carly.ui.maps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.am.carly.data.repository.UserRepository

class MapViewViewModelFactory constructor(private val userRepository: UserRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MapViewViewModel::class.java)) {
            MapViewViewModel(this.userRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}

