package com.am.carly.data.model

import com.google.firebase.firestore.GeoPoint

data class Car(
    var name: String = "",
    var imagesList: List<String> = listOf(),
    var rating: Int? = 0,
    var pricePerDay: String = "",
    var description: String = "",
    var isFullToFullPolicy: Boolean = false,
    var automaticTransmission: Boolean = false,
    var doorCount: String = "",
    var location: GeoPoint? = GeoPoint(0.0, 0.0),
    var city: String = "",
    var ownerId: String = ""
)