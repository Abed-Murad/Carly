package com.am.carly.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Card(
    var cardNumber: String = "",
    var expire: String = "",
    var cvv: String = "",
    var nameOnCard: String = ""

) : Parcelable