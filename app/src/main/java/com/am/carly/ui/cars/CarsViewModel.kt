package com.am.carly.ui.cars

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.am.carly.data.repository.UserRepository

class CarsViewModel(var userRepository: UserRepository) : ViewModel() {

    var isLoading: LiveData<Boolean> = MutableLiveData()
    fun getLoginInfo() = userRepository.getLoginInfo()

}