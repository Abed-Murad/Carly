package com.am.carly.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.am.carly.data.local.LoginPref
import com.am.carly.data.model.LoginInfo
import com.am.carly.data.remote.MyApi

class UserRepository(
    private val mApi: MyApi,
    private val mLoginPref: LoginPref
) {
    fun getLoginInfo(): LiveData<LoginInfo> {
        val data = MutableLiveData<LoginInfo>()
        data.value = mLoginPref.getLoginInfo()
        return data

    }

}