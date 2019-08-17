package com.am.carly.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.am.carly.ui.base.BaseActivity
import com.am.carly.ui.login.StartActivity
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SplashScreenActivity : BaseActivity(), KodeinAware {
    override val kodein by kodein()
    private val mFactory: SplashViewModelFactory by instance()
    private lateinit var mSplashViewModel: SplashViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mSplashViewModel = ViewModelProviders.of(this, mFactory).get(SplashViewModel::class.java)
        mSplashViewModel.getLoginInfo().observe(this, Observer {
            startStartActivity()
        })
    }

    private fun startStartActivity() {
        startActivity(Intent(this, StartActivity::class.java))
    }


}
