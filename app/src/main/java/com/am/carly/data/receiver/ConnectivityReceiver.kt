package com.am.carly.data.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import com.am.carly.R

class ConnectivityReceiver : BroadcastReceiver() {

    private var connectivityReceiverAlertDialog: AlertDialog? = null

    override fun onReceive(context: Context, intent: Intent) {

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val connectionState = connectivityManager.activeNetworkInfo

        if (connectivityReceiverAlertDialog == null) {
            connectivityReceiverAlertDialog = AlertDialog.Builder(context)
                .setTitle(context.getString(R.string.internet_connection_required))
                .setMessage(context.getString(R.string.internet_connection_required_message))
                .setIcon(R.drawable.ic_error)
                .setCancelable(false)
                .setPositiveButton(context.getString(R.string.go_to_settings)) { _, _ ->
                    context.startActivity(Intent(Settings.ACTION_WIRELESS_SETTINGS))
                }
                .create()
        }

        if (connectionState == null || !connectionState.isConnected)
            connectivityReceiverAlertDialog!!.show()
        else
            connectivityReceiverAlertDialog!!.dismiss()
    }
}