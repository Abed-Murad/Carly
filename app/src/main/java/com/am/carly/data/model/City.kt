package com.am.carly.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class City(
    var id: Int = 0,
    var name: String = "",
    var imgPath: String = ""
)