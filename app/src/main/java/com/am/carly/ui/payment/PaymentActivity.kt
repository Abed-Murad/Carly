package com.am.carly.ui.payment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.am.carly.R
import com.am.carly.data.model.Card
import com.am.carly.data.model.Order
import com.am.carly.databinding.ActivityPaymentBinding
import com.am.carly.ui.base.BaseActivity
import com.am.carly.util.PARM_ORDER_MODEL
import com.am.carly.util.insertPeriodically
import kotlinx.android.synthetic.main.activity_payment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


class PaymentActivity : BaseActivity(), KodeinAware {
    override val kodein by kodein()
    private val mFactory: PaymentActivityViewModelFactory by instance()
    private lateinit var mBinding: ActivityPaymentBinding
    private lateinit var mViewModel: PaymentActivityViewModel
    private lateinit var mOrder: Order
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mOrder = intent.extras.getParcelable(PARM_ORDER_MODEL)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_payment)
        mViewModel = ViewModelProviders.of(this, mFactory).get(PaymentActivityViewModel::class.java)
        mViewModel.order = mOrder
        mBinding.viewModel = mViewModel

        setupTextChangeListeners()
        payBtn.setOnClickListener {
            submitAction()
        }
    }

    private fun submitAction() {
        progress_bar.visibility = View.VISIBLE
        payBtn.alpha = 0f

        Handler().postDelayed({
            showDialogPaymentSuccess()
            progress_bar.visibility = View.GONE
            payBtn.alpha = 1f
        }, 1000)
    }

    private fun showDialogPaymentSuccess() {
        mViewModel.order.card = Card(
            mViewModel.cardNumber,
            mViewModel.expire,
            mViewModel.cvv,
            mViewModel.nameOnCard
        )
        startActivity(Intent(this@PaymentActivity, PaymentSuccessActivity::class.java).also {
            it.putExtra(PARM_ORDER_MODEL, mViewModel.order)

        })
    }

    private fun setupTextChangeListeners() {
        et_card_number.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, count: Int) {
                if (charSequence.toString().trim { it <= ' ' }.isEmpty()) {
                    card_number.text = "**** **** **** ****"
                } else {
                    val number =
                        insertPeriodically(charSequence.toString().trim { it <= ' ' }, " ", 4)
                    card_number.text = number
                }
            }

            override fun afterTextChanged(editable: Editable) {

            }
        })

        et_expire.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, count: Int) {
                if (charSequence.toString().trim { it <= ' ' }.isEmpty()) {
                    card_expire.text = "MM/YY"
                } else {
                    val exp = insertPeriodically(charSequence.toString().trim { it <= ' ' }, "/", 2)
                    card_expire.text = exp
                }
            }

            override fun afterTextChanged(editable: Editable) {

            }
        })

        et_cvv.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, count: Int) {
                if (charSequence.toString().trim { it <= ' ' }.isEmpty()) {
                    card_cvv.text = "***"
                } else {
                    card_cvv.text = charSequence.toString().trim { it <= ' ' }
                }
            }

            override fun afterTextChanged(editable: Editable) {

            }
        })

        et_name.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, count: Int) {
                if (charSequence.toString().trim { it <= ' ' }.isEmpty()) {
                    card_name.text = "Your Name"
                } else {
                    card_name.text = charSequence.toString().trim { it <= ' ' }
                }
            }

            override fun afterTextChanged(editable: Editable) {

            }
        })
    }


}