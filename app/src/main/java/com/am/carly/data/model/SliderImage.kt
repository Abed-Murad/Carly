package com.am.carly.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize data class SliderImage(var title: String = "", var imageUrl: String = "") : Parcelable