package com.am.carly.ui.rent

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.am.carly.data.model.Car
import com.am.carly.databinding.ActivityDateRangeBinding
import com.am.carly.ui.base.BaseActivity
import com.am.carly.util.PARM_CAR_MODEL
import com.squareup.timessquare.CalendarPickerView
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import java.util.*



class DateRangeActivity : BaseActivity(), KodeinAware {
    override val kodein by kodein()
    private val mFactory: DateRangeViewModelFactory by instance()
    private lateinit var mBinding: ActivityDateRangeBinding
    private lateinit var mViewModel: DateRangeViewModel
    private lateinit var mCar: Car


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mCar = intent.extras.getParcelable(PARM_CAR_MODEL)
        mBinding = DataBindingUtil.setContentView(this, com.am.carly.R.layout.activity_date_range)
        mViewModel = ViewModelProviders.of(this, mFactory).get(DateRangeViewModel::class.java)
        mViewModel.car = mCar
        mBinding.viewModel = mViewModel

        setupDateRangeView()


    }

    private fun setupDateRangeView() {
        val today = Date()
        val nextYear = Calendar.getInstance()
        nextYear.add(Calendar.YEAR, 1)

        mBinding.calendarPickerView.setOnDateSelectedListener(object : CalendarPickerView.OnDateSelectedListener {
            override fun onDateSelected(date: Date?) {
                val calSelected = Calendar.getInstance()
                calSelected.time = date

                mViewModel.selectedDate = mBinding.calendarPickerView.selectedDates


            }

            override fun onDateUnselected(date: Date?) {
            }

        })


        mBinding.calendarPickerView.setOnInvalidDateSelectedListener { }
        mBinding.calendarPickerView.init(today, nextYear.time).inMode(CalendarPickerView.SelectionMode.RANGE)
    }

}