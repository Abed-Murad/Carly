package com.am.carly.data.model

import android.os.Parcelable
import com.google.firebase.Timestamp
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Order(
    var ownerId: String,
    var clientId: String,
    var card: Card,
    var car: Car,
    var location: String,
    var startDate: Timestamp,
    var endDate: Timestamp,
    var daysCount:Int,
    var isOrderActive: Boolean
) : Parcelable