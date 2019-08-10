package com.am.carly.ui.login

import android.os.Bundle
import com.am.carly.ui.base.BaseActivity
import org.kodein.di.KodeinAware

class LoginActivity : BaseActivity(), KodeinAware {
    override val kodein by org.kodein.di.android.kodein()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

}