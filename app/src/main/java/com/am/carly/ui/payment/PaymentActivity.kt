package com.am.carly.ui.payment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.am.carly.R
import com.am.carly.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_payment.*

class PaymentActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

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
        startActivity(Intent(this@PaymentActivity, PaymentSuccessActivity::class.java))
    }

    private fun setupTextChangeListeners() {
        et_card_number.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, count: Int) {
                if (charSequence.toString().trim { it <= ' ' }.isEmpty()) {
                    card_number.text = "**** **** **** ****"
                } else {
                    val number = insertPeriodically(charSequence.toString().trim { it <= ' ' }, " ", 4)
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

    fun insertPeriodically(text: String, insert: String, period: Int): String {
        val builder = StringBuilder(text.length + insert.length * (text.length / period) + 1)
        var index = 0
        var prefix = ""
        while (index < text.length) {
            builder.append(prefix)
            prefix = insert
            builder.append(text.substring(index, Math.min(index + period, text.length)))
            index += period
        }
        return builder.toString()
    }

}