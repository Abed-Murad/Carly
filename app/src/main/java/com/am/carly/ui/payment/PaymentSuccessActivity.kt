package com.am.carly.ui.payment

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.am.carly.R
import com.am.carly.data.model.Order
import com.am.carly.databinding.ActivityPaymentSuccessBinding
import com.am.carly.ui.base.BaseActivity
import com.am.carly.util.PARM_ORDER_MODEL
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class PaymentSuccessActivity : BaseActivity()  , KodeinAware {
    override val kodein by kodein()
    private val mFactory: PaymentSuccessActivityViewModelFactory by instance()
    private lateinit var mBinding: ActivityPaymentSuccessBinding
    private lateinit var mViewModel: PaymentSuccessActivityViewModel
    private lateinit var mOrder: Order

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mOrder = intent.extras.getParcelable(PARM_ORDER_MODEL)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_payment_success)
        mViewModel = ViewModelProviders.of(this, mFactory).get(PaymentSuccessActivityViewModel::class.java)
        mViewModel.order = mOrder
        mBinding.viewModel = mViewModel
    }
}