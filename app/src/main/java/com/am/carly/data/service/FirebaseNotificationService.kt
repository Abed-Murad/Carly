package com.am.carly.data.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.am.carly.R
import com.am.carly.ui.splash.SplashScreenActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import org.json.JSONObject


/**
 *  use PostMan to test this notification by sending
 *
 *  1- a post Request to https://fcm.googleapis.com/fcm/send
 *
 *  2- the Headers
 *  Content-Type:application/json
 *  Authorization:key=AAAAERgRqLU:APA91bGs6CX7qeRdzmhUXusvoTw5N1Jw1ikD3VN2rhR7UhUE4_QXMAHfipOUvEKmoeyISM7HARvTaEmBBGv2Tc_v6xjtx_LdIaRG8ylOJC5YTHcPzzmr4IbX6hkMMvcKWob2WjQQzLrb
 *
 *  3- the body
 *   {
 *      "to": "/topics/TEST",
 *
 *       "data": {
 *       "extra_inforamtion": "This is a Firebase Cloud Messaging TEST topic message!"
 *       }
 *   }
 *
 */
class FirebaseNotificationService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {

        // Check if message contains a data payload.
        remoteMessage?.data?.isNotEmpty()?.let {
            Log.d(TAG, "Message data payload: ${remoteMessage.data}")
            val dataJSOJN = JSONObject(remoteMessage.data)
            val extraInformation = dataJSOJN.getString("extra_inforamtion")
            sendNotification(extraInformation)

            // Check if message contains a notification payload.
            remoteMessage.notification?.let {
                Log.d(TAG, "Message Notification Body: ${it.body}")
            }
        }

    }

    private fun sendNotification(messageBody: String) {
        val intent = Intent(this, SplashScreenActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent = PendingIntent.getActivity(
            this, REQUEST_MAIN_ACTIVITY_FROM_NOTIFICATION, intent, PendingIntent.FLAG_ONE_SHOT
        )

        val largeIcon = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
        val channelId = getString(R.string.default_notification_channel_id)

        val notification = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Content Title")
            .setContentText(messageBody)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setLargeIcon(largeIcon)
            .setOnlyAlertOnce(true)
            .addAction(R.mipmap.ic_launcher, "Accept", pendingIntent)
            .addAction(R.mipmap.ic_launcher, "Decline", pendingIntent)
            .setColor(Color.BLUE)
            .build()

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Channel human readable title",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(0 /* ID of notification */, notification)
    }

    /** Called if InstanceID token is updated. */
    override fun onNewToken(token: String?) {
        // send the Instance ID token to your app server.
        sendRegistrationToServer(token)
    }

    private fun sendRegistrationToServer(token: String?) {
        Log.d(TAG, "Token: $token")
        //  TODO("Send New InstanceID to Our Servers.")
    }

    companion object {

        private val TAG = FirebaseNotificationService::class.java.simpleName
        private const val REQUEST_MAIN_ACTIVITY_FROM_NOTIFICATION = 5500
    }
}