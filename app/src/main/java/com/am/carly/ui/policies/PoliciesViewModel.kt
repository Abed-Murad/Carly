package com.am.carly.ui.policies

import android.view.View
import androidx.lifecycle.ViewModel
import com.am.carly.data.repository.UserRepository
import com.am.carly.ui.base.BaseActivity

class PoliciesViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun onListViewBtnClick(view: View) {
        (view.context as BaseActivity).onBackPressed()
    }
}