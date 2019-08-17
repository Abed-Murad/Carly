package com.am.carly.data.model

import android.os.Parcelable
import com.google.firebase.firestore.GeoPoint
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

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
    var location: @RawValue GeoPoint = GeoPoint(5.0, 5.1),
    var city: String = "",
    var ownerId: String = ""
) : Parcelable