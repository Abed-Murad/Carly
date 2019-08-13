package com.am.carly.data.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.GeoPoint

data class Order(
    var ownerId: String,
    var clientId: String,
    var carId: String,
    var location: GeoPoint,
    var startDate: Timestamp,
    var endDate: Timestamp,
    var isOrderActive:Boolean
)