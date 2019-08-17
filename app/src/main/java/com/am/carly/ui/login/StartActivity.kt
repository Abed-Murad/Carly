package com.am.carly.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.am.carly.R
import com.am.carly.databinding.ActivityStartBinding
import com.am.carly.ui.base.BaseActivity
import com.am.carly.ui.cities.CitiesActivity
import com.google.firebase.auth.FirebaseAuth
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class StartActivity : BaseActivity(), KodeinAware {
    override val kodein by kodein()
    private val mFactory: StartActivityViewModelFactory by instance()
    private lateinit var mBinding: ActivityStartBinding
    private lateinit var mViewModel: StartActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        checkIfUserLoggedIn()
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_start)
        mViewModel = ViewModelProviders.of(this, mFactory).get(StartActivityViewModel::class.java)
        mBinding.viewModel = mViewModel

    }

    private fun checkIfUserLoggedIn() {
        if (FirebaseAuth.getInstance().currentUser != null) {
            startActivity(Intent(this@StartActivity, CitiesActivity::class.java))
            this@StartActivity.finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        checkIfUserLoggedIn()
        super.onActivityResult(requestCode, resultCode, data)

    }


}