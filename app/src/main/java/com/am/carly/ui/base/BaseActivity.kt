package com.am.carly.ui.base

import android.annotation.SuppressLint
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.am.carly.data.receiver.ConnectivityReceiver


@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    private val mIntentFilter: IntentFilter = IntentFilter()
    private var mConnectivityReceiver: ConnectivityReceiver =
        ConnectivityReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mIntentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(mConnectivityReceiver, mIntentFilter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(mConnectivityReceiver)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
//                val intent = Intent(this, MainActivity::class.java)
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
//                startActivity(intent)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
