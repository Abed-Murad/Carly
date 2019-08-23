package com.am.carly.ui.login

import android.view.View
import androidx.lifecycle.ViewModel
import com.am.carly.BuildConfig
import com.am.carly.R
import com.am.carly.data.repository.UserRepository
import com.am.carly.ui.base.BaseActivity
import com.firebase.ui.auth.AuthUI

class StartActivityViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun startFirebaseAuthUiActivity(view: View) {
        val URL_TERMS_OF_SERVICE = "https://ee35b44c-72fa-4155-9eb0-0ea6f4026dfc.htmlpasta.com/"
        val FIREBASE_UI_SIGN_IN_REQUEST_CODE = 1010

        val providers = listOf(
            AuthUI.IdpConfig.GoogleBuilder().build(),
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.PhoneBuilder().build()
        )

        val intent = AuthUI.getInstance().createSignInIntentBuilder()
            .setIsSmartLockEnabled(!BuildConfig.DEBUG, true)
            .setAvailableProviders(providers)
            .setLogo(R.drawable.ic_launcher_trans)
            .setTheme(R.style.LoginTheme)
            .setTosAndPrivacyPolicyUrls(URL_TERMS_OF_SERVICE, URL_TERMS_OF_SERVICE)
            .build()

        (view.context as BaseActivity).startActivityForResult(
            intent,
            FIREBASE_UI_SIGN_IN_REQUEST_CODE
        )
    }
}