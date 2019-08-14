package com.am.carly.util

import android.app.Application
import com.am.carly.data.local.LoginPref
import com.am.carly.data.remote.MyApi
import com.am.carly.data.repository.UserRepository
import com.am.carly.ui.base.viewmodelfactory.*
import com.google.firebase.analytics.FirebaseAnalytics
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MyApplication : Application(), KodeinAware {

    //Kodein Dependency Injection
    override val kodein = Kodein.lazy {
        import(androidXModule(this@MyApplication))
        bind() from singleton { LoginPref(instance()) }
        bind() from singleton { MyApi() }
        bind() from provider { SplashViewModelFactory(instance()) }
        bind() from provider { CitiesViewModelFactory(instance()) }
        bind() from provider { AddCarViewModelFactory(instance()) }
        bind() from provider { CarsViewModelFactory(instance()) }
        bind() from provider { ChooseLocationViewModelFactory(instance()) }
        bind() from provider { PoliciesViewModelFactory(instance()) }
        bind() from provider { MapViewViewModelFactory(instance()) }
        bind() from provider { DateRangeViewModelFactory(instance()) }
        bind() from provider { CarDetailsViewModelFactory(instance()) }
        bind() from singleton { UserRepository(instance(), instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        FirebaseAnalytics.getInstance(this)
    }
}