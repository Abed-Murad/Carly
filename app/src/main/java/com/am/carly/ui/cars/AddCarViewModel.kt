package com.am.carly.ui.cars

import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.am.carly.data.model.Car
import com.am.carly.data.repository.UserRepository
import com.am.carly.ui.maps.ChooseLocationActivity
import com.am.carly.util.PARM_CAR_MODEL
import com.am.carly.util.PARM_INTENT_SOURCE
import com.esafirm.imagepicker.features.ImagePicker

class AddCarViewModel(var userRepository: UserRepository) : ViewModel() {

    var name: String = ""
    var pricePerDay: String = ""
    var description: String = ""
    var doorsCount: String = ""
    var transmission: Boolean = false
    var isFullToFull: Boolean = true


    fun onNextBtnClick(view: View) {
        val car = Car(
            name = name,
            rating = 50,
            pricePerDay = pricePerDay,
            description = description,
            isFullToFullPolicy = isFullToFull,
            automaticTransmission = transmission,
            doorCount = doorsCount,
            city = "Gaza"
        )

//        FirebaseFirestore.getInstance().collection("cars_gaza").document("one")
//            .set(car)
//            .addOnSuccessListener { Log.d("ttt", "DocumentSnapshot successfully written!") }
//            .addOnFailureListener { e -> Log.w("ttt", "Error writing document", e) }
//
        view.context.startActivity(Intent(view.context, ChooseLocationActivity::class.java).also {
            it.putExtra(PARM_CAR_MODEL, car)
            it.putExtra(PARM_INTENT_SOURCE, "add_car_activity")
        })
    }

    fun pickImagesBtnClick(view: View) {
        ImagePicker.create(view.context as AppCompatActivity).start()
    }
}