package com.am.carly.ui.policies

import android.os.Bundle
import android.webkit.WebView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.am.carly.R
import com.am.carly.data.model.Car
import com.am.carly.data.model.Order
import com.am.carly.databinding.ActivityPoliciesBinding
import com.am.carly.ui.base.BaseActivity
import com.am.carly.util.ARG_ADD_CAR_ACTIVITY
import com.am.carly.util.PARM_CAR_MODEL
import com.am.carly.util.PARM_INTENT_SOURCE
import com.am.carly.util.PARM_ORDER_MODEL
import com.google.android.gms.maps.GoogleMap
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class PoliciesActivity : BaseActivity(), KodeinAware {
    override val kodein by kodein()
    private val mFactory: PoliciesViewModelFactory by instance()
    private lateinit var mBinding: ActivityPoliciesBinding
    private lateinit var mViewModel: PoliciesViewModel
    private var mGoogleMap: GoogleMap? = null
    private lateinit var mOrder: Order
    private lateinit var mCar: Car
    private lateinit var intentSource: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intentSource = intent.extras.getString(PARM_INTENT_SOURCE)
        mViewModel = ViewModelProviders.of(this, mFactory).get(PoliciesViewModel::class.java)
        mViewModel.intentSource = intentSource
        if (intentSource == ARG_ADD_CAR_ACTIVITY) {
            mCar = intent.extras.getParcelable(PARM_CAR_MODEL)
            mViewModel.car = mCar
        } else {
            mOrder = intent.extras.getParcelable(PARM_ORDER_MODEL)
            mViewModel.order = mOrder
        }
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_policies)
        mBinding.viewModel = mViewModel


        initWebView()
    }

    private fun initWebView() {
        val mWebView = findViewById<WebView>(R.id.webView)
        val webSettings = mWebView.settings
        webSettings.javaScriptEnabled = true
        mWebView.loadUrl("file:///android_asset/Policies.html")
    }

}