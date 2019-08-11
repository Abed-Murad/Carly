package com.am.carly.ui.policies

import android.os.Bundle
import android.webkit.WebView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.am.carly.R
import com.am.carly.databinding.ActivityPoliciesBinding
import com.am.carly.ui.base.BaseActivity
import com.am.carly.ui.base.viewmodelfactory.PoliciesViewModelFactory
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_policies)
        mViewModel = ViewModelProviders.of(this, mFactory).get(PoliciesViewModel::class.java)
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