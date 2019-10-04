package com.am.carly.ui.payment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.am.carly.data.model.Order
import com.am.carly.databinding.ActivityPaymentSuccessBinding
import com.am.carly.ui.base.BaseActivity
import com.am.carly.util.PARM_ORDER_MODEL
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_payment_success.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import java.text.SimpleDateFormat
import java.util.*


class PaymentSuccessActivity : BaseActivity(), KodeinAware {
    override val kodein by kodein()
    private val mFactory: PaymentSuccessActivityViewModelFactory by instance()
    private lateinit var mBinding: ActivityPaymentSuccessBinding
    private lateinit var mViewModel: PaymentSuccessActivityViewModel
    private lateinit var mOrder: Order

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mOrder = intent.extras.getParcelable(PARM_ORDER_MODEL)
        mBinding =
            DataBindingUtil.setContentView(this, com.am.carly.R.layout.activity_payment_success)
        mViewModel =
            ViewModelProviders.of(this, mFactory).get(PaymentSuccessActivityViewModel::class.java)
        mViewModel.order = mOrder
        mBinding.viewModel = mViewModel


        val amount = mOrder.daysCount * mOrder.car!!.pricePerDay.toInt()
        amountTextView.text = "$$amount"

        val date = SimpleDateFormat("EEE, MMM d, ''yy")
        val time = SimpleDateFormat("h:mm a")
        val dateString = date.format(Calendar.getInstance().time)
        val timeString = time.format(Calendar.getInstance().time)

        currentDateTextView.text = dateString
        currentTimeTextView.text = timeString

        val user = FirebaseAuth.getInstance().currentUser
        userEmailTextView.text = "Abdallah.Murad@Protonmail.com"
        userNameTextView.text = "Abdallah Murad"
        Glide.with(this).load("https://i1.rgstatic.net/ii/profile.image/695943527690240-1542937275015_Q512/Abdallah_Murad.jpg").into(userImageView)
    }
}