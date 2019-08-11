package com.am.carly.ui.cars
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.am.carly.R
import com.am.carly.databinding.ActivityCarDetails2Binding
import com.am.carly.ui.base.BaseActivity
import com.am.carly.ui.base.viewmodelfactory.CarDetailsViewModelFactory
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class CarDetailsActivity : BaseActivity(), KodeinAware {
    override val kodein by kodein()
    private val mFactory: CarDetailsViewModelFactory by instance()
    private lateinit var mBinding: ActivityCarDetails2Binding
    private lateinit var mViewModel: CarDetailsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_car_details2)
        mViewModel = ViewModelProviders.of(this, mFactory).get(CarDetailsViewModel::class.java)

    }
}