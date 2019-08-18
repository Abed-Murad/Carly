package com.am.carly.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import com.am.carly.R
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*


object FAKE {

    //Dummy
    val IMG_URL_FOOTBALL = "https://images.carscoops.com/2018/07/a3f42202-a4.jpg"
    val IMG_URL_STARS = "https://cdn.pixabay.com/photo/2016/03/23/07/44/bmw-1274292_960_720.jpg"
}

    fun getGazaFakeMarkersList(context: Context): List<MarkerOptions> {
        val markerOptionsList = ArrayList<MarkerOptions>()
        val carMarker = getCarMarker(context)
        markerOptionsList.add(
            MarkerOptions()
                .position(LatLng(31.300602, 34.241314))
                .title("Nissan Versa")
                .snippet("250 NIS")
                .icon(BitmapDescriptorFactory.fromBitmap(carMarker))
        )
        markerOptionsList.add(
            MarkerOptions()
                .position(LatLng(31.259279, 34.263260))
                .title("Toyota Yaris")
                .snippet("210 NIS")
                .icon(BitmapDescriptorFactory.fromBitmap(carMarker))
        )
        markerOptionsList.add(
            MarkerOptions()
                .position(LatLng(31.346530, 34.300222))
                .title("Ford Focus")
                .snippet("220 NIS")
                .icon(BitmapDescriptorFactory.fromBitmap(carMarker))
        )
        markerOptionsList.add(
            MarkerOptions()
                .position(LatLng(31.324700, 34.330304))
                .title("Nissan Sunny")
                .snippet("230 NIS")
                .icon(BitmapDescriptorFactory.fromBitmap(carMarker))
        )
        markerOptionsList.add(
            MarkerOptions()
                .position(LatLng(31.382796, 34.338308))
                .title("Hyundai Accent")
                .snippet("240 NIS")
                .icon(BitmapDescriptorFactory.fromBitmap(carMarker))
        )
        markerOptionsList.add(
            MarkerOptions()
                .position(LatLng(31.436887, 34.358864))
                .title("Toyota Corolla")
                .snippet("250 NIS")
                .icon(BitmapDescriptorFactory.fromBitmap(carMarker))
        )
        markerOptionsList.add(
            MarkerOptions()
                .position(LatLng(31.445122, 34.395907))
                .title("Nissan Micra")
                .snippet("150 NIS")
                .icon(BitmapDescriptorFactory.fromBitmap(carMarker))
        )
        markerOptionsList.add(
            MarkerOptions()
                .position(LatLng(31.478255, 34.404397))
                .title("Nissan Sunny ")
                .snippet("120 NIS")
                .icon(BitmapDescriptorFactory.fromBitmap(carMarker))
        )
        markerOptionsList.add(
            MarkerOptions()
                .position(LatLng(31.491512, 34.456591))
                .title("Kia Soul")
                .snippet("140 NIS")
                .icon(BitmapDescriptorFactory.fromBitmap(carMarker))
        )
        markerOptionsList.add(
            MarkerOptions()
                .position(LatLng(31.519426, 34.445563))
                .title("Kia Picanto")
                .snippet("160 NIS")
                .icon(BitmapDescriptorFactory.fromBitmap(carMarker))
        )
        markerOptionsList.add(
            MarkerOptions()
                .position(LatLng(31.532181, 34.495751))
                .title("Toyota Corolla")
                .snippet("350 NIS")
                .icon(BitmapDescriptorFactory.fromBitmap(carMarker))
        )
        markerOptionsList.add(
            MarkerOptions()
                .position(LatLng(31.555109, 34.520728))
                .title("Kia Rio")
                .snippet("250 NIS")
                .icon(BitmapDescriptorFactory.fromBitmap(carMarker))
        )

        return markerOptionsList
    }


    fun getWestBankFakeMarkersList(context: Context): List<MarkerOptions> {
    val markerOptionsList = ArrayList<MarkerOptions>()
    val carMarker = getCarMarker(context)
    markerOptionsList.add(
        MarkerOptions()
            .position(LatLng(32.244486, 35.281339))
            .title("Nissan Versa")
            .snippet("250 NIS")
            .icon(BitmapDescriptorFactory.fromBitmap(carMarker))
    )
    markerOptionsList.add(
        MarkerOptions()
            .position(LatLng(32.098205, 35.394693))
            .title("Toyota Yaris")
            .snippet("210 NIS")
            .icon(BitmapDescriptorFactory.fromBitmap(carMarker))
    )
    markerOptionsList.add(
        MarkerOptions()
            .position(LatLng(31.903091, 35.382670))
            .title("Ford Focus")
            .snippet("220 NIS")
            .icon(BitmapDescriptorFactory.fromBitmap(carMarker))
    )
    markerOptionsList.add(
        MarkerOptions()
            .position(LatLng(31.676344, 35.286802))
            .title("Nissan Sunny")
            .snippet("230 NIS")
            .icon(BitmapDescriptorFactory.fromBitmap(carMarker))
    )
    markerOptionsList.add(
        MarkerOptions()
            .position(LatLng(31.525426, 35.099435))
            .title("Hyundai Accent")
            .snippet("240 NIS")
            .icon(BitmapDescriptorFactory.fromBitmap(carMarker))
    )
    markerOptionsList.add(
        MarkerOptions()
            .position(LatLng(31.428824, 34.999001))
            .title("Toyota Corolla")
            .snippet("250 NIS")
            .icon(BitmapDescriptorFactory.fromBitmap(carMarker))
    )
    markerOptionsList.add(
        MarkerOptions()
            .position(LatLng(31.911471, 35.167825))
            .title("Nissan Micra")
            .snippet("150 NIS")
            .icon(BitmapDescriptorFactory.fromBitmap(carMarker))
    )
    return markerOptionsList
}


fun getCarMarker(context: Context): Bitmap {
    val markerHeight = 75
    val markerWidth = 75
    val bitmapDrawable = context.resources.getDrawable(R.drawable.ic_car_marker) as BitmapDrawable
    val bitmap = bitmapDrawable.bitmap
    return Bitmap.createScaledBitmap(bitmap, markerWidth, markerHeight, false)
}
