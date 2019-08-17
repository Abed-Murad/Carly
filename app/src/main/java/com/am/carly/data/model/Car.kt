package com.am.carly.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Car(
    var name: String = "",
    var imagesList: ArrayList<String>? = null,
    var rating: Int? = 0,
    var pricePerDay: String = "",
    var description: String = "",
    var isFullToFullPolicy: Boolean = false,
    var automaticTransmission: Boolean = false,
    var doorCount: String = "",
    var location:  String = "",
    var city: String = "",
    var ownerId: String = ""
) : Parcelable