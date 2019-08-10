package com.am.carly.ui.cars

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.am.carly.R
import com.am.carly.databinding.ActivityCarsBinding
import com.am.carly.ui.base.BaseActivity
import com.am.carly.ui.base.viewmodelfactory.CarsViewModelFactory
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class CarsActivity : BaseActivity(), KodeinAware {
    override val kodein by kodein()
    private val mFactory: CarsViewModelFactory by instance()
    private lateinit var mBinding: ActivityCarsBinding
    private lateinit var mViewModel: CarsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_cars)
        mViewModel = ViewModelProviders.of(this, mFactory).get(CarsViewModel::class.java)
    }
}