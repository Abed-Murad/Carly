package com.am.carly.data.local

import android.content.Context
import android.content.SharedPreferences
import com.am.carly.data.model.LoginInfo

class LoginPref(context: Context) {
    // Application Context is used to avoid memory leak.
    private val preference: SharedPreferences = context.getSharedPreferences(PREFS_LOGIN_CONFIG, Context.MODE_PRIVATE)

    fun getPref(): SharedPreferences {
        return preference
    }

    private fun putString(key: String, value: String?): Boolean {
        return preference.edit()!!.putString(key, value)!!.commit()
    }

    fun getString(key: String, defValue: String): String? {
        return preference.getString(key, defValue)
    }

    private fun putInt(key: String, value: Int): Boolean {
        return preference.edit()!!.putInt(key, value)!!.commit()
    }

    fun getInt(key: String, defValue: Int): Int {
        return preference.getInt(key, defValue)
    }

    fun getLoginInfo(): LoginInfo {
        val loginConfig = LoginInfo()
        loginConfig.token = preference.getString(KEY_TOKEN_ID, "")
        loginConfig.userId = preference.getString(KEY_USER_ID, "")
        loginConfig.isUserApproved = preference.getBoolean(KEY_User_APPROVED, false)
        return loginConfig
    }

    fun updateDriverInfo(loginInfo: LoginInfo) {
        putString(KEY_TOKEN_ID, loginInfo.token)
        putString(KEY_USER_ID, loginInfo.userId)
        putBoolean(KEY_User_APPROVED, loginInfo.isUserApproved)
    }

    fun putBoolean(key: String, value: Boolean) {
        (preference.edit()!!.putBoolean(key, value)!!).apply()
    }

    companion object {
        const val PREFS_LOGIN_CONFIG = "prefs_login_config"

        const val KEY_TOKEN_ID = "token"
        const val KEY_USER_ID = "user_id"
        const val KEY_User_APPROVED = "is_user_approved"
    }
}