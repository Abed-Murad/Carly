package com.am.carly.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.am.carly.BuildConfig
import com.am.carly.R
import com.am.carly.ui.cities.CitiesActivity
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        logInButton.setOnClickListener {
            startFirebaseUiForAuth()

        }
        SignUpButton.setOnClickListener {
            startFirebaseUiForAuth()

        }
    }


    private fun startFirebaseUiForAuth() {
        val URL_TERMS_OF_SERVICE = "https://www.kabam.com/corporate/terms-of-service.html"
        val FIREBASE_UI_SIGN_IN_REQUEST_CODE = 1010

        val providers = listOf(
            AuthUI.IdpConfig.GoogleBuilder().build(),
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.PhoneBuilder().build()
        )

        val intent = AuthUI.getInstance().createSignInIntentBuilder()
            .setIsSmartLockEnabled(!BuildConfig.DEBUG, true)
            .setAvailableProviders(providers)
            .setLogo(R.drawable.ic_launcher)
            .setTheme(R.style.LoginTheme)
            .setTosAndPrivacyPolicyUrls(URL_TERMS_OF_SERVICE, URL_TERMS_OF_SERVICE)
            .build()

        startActivityForResult(intent, FIREBASE_UI_SIGN_IN_REQUEST_CODE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null && currentUser.email != null) {
            startActivity(Intent(this@StartActivity , CitiesActivity::class.java))
        }
    }

}