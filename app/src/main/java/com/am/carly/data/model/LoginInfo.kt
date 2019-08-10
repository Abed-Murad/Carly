package com.am.carly.data.model


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginInfo(
    var token: String? = null,
    var userId: String? = "",
    var isUserApproved: Boolean = false
) : Parcelable

