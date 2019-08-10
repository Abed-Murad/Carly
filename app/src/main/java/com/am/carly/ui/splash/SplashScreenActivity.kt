package com.am.carly.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.am.carly.BuildConfig
import com.am.carly.R
import com.am.carly.data.model.LoginInfo
import com.am.carly.ui.MainActivity
import com.am.carly.ui.base.viewmodelfactory.SplashViewModelFactory
import com.am.carly.ui.login.LoginActivity
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SplashScreenActivity : AppCompatActivity(), KodeinAware {
    override val kodein by kodein()
    private val mFactory: SplashViewModelFactory by instance()
    private lateinit var mSplashViewModel: SplashViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mSplashViewModel = ViewModelProviders.of(this, mFactory).get(SplashViewModel::class.java)
        mSplashViewModel.getLoginInfo().observe(this, Observer { loginInfo ->

            val currentUser = FirebaseAuth.getInstance().currentUser
            if (currentUser != null) {
                startActivity(Intent(this, MainActivity::class.java))
            }
            if (!loginInfo.token.isNullOrEmpty()) {
                startMainActivity(loginInfo)
            } else {
//                startLoginActivity()
                startFirebaseUiForAuth()

            }
        })
    }

    private fun startLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun startMainActivity(loginInfo: LoginInfo) {
        startActivity(Intent(this, MainActivity::class.java).also {
            //            it.putExtra(Constants.KEY_DRIVER_INFO, driverInfo)
        })
        finish()
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
    }
}
