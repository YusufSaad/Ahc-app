package org.abuhuraira.app.common.app

import android.content.Intent
import android.support.v4.content.LocalBroadcastManager
import com.google.firebase.iid.FirebaseInstanceIdService
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import android.app.NotificationManager
import android.media.RingtoneManager
import android.app.PendingIntent
import android.support.v4.app.NotificationCompat
import android.app.NotificationChannel
import android.content.Context
import android.graphics.Color
import org.abuhuraira.app.R
import com.example.coreandroid.sources.dependencies.HasDependencies
import com.example.coreandroid.sources.enums.DefaultsKeys.Companion.userID
import com.example.coreandroid.sources.extensions.ACTION_REFRESHED_DEVICE_TOKEN
import com.example.coreandroid.sources.extensions.DEVICE_TOKEN
import com.example.coreandroid.sources.logging.LogHelper


/**
 * Created by ahmedsaad on 2018-02-06.
 * Copyright © 2017. All rights reserved.
 */
class NotificationInstanceService: FirebaseInstanceIdService() {

    override fun onTokenRefresh() {
        // Get updated InstanceID token.
         val refreshedToken = FirebaseInstanceId.getInstance().token
        LogHelper.i(messages = *arrayOf("Refreshed token: $refreshedToken"))

        val intent = Intent()
        intent.action = ACTION_REFRESHED_DEVICE_TOKEN
        intent.putExtra(DEVICE_TOKEN, refreshedToken)

        LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(intent)
    }
}

class NotificationMessageService: FirebaseMessagingService(), HasDependencies {

    private val preferencesWorker by lazy {
        dependencies.resolvePreferencesWorker()
    }

    private val dataWorker by lazy {
        dependencies.resolveDataWorker()
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)

        if (remoteMessage == null) return

        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        LogHelper.d(messages = *arrayOf("From: " + remoteMessage.from))

        // Check if message contains a data payload.
        if (remoteMessage.data.isNotEmpty()) {
            LogHelper.d(messages = *arrayOf("Message data payload: " + remoteMessage.data))

            val data = remoteMessage.data
            val message = data["body"]
            val title = data["title"]

            var intent: Intent? = null

            // TODO: Add logic here

            sendNotification(message = message, title = title, intent = intent)

        }

        // Check if message contains a notification payload.
        if (remoteMessage.notification != null) {
            LogHelper.d(messages = *arrayOf("Message Notification Body: "
                    + remoteMessage.notification?.body))
        }
    }

    /**
     * Create and show a notification containing the received GCM message.
     *
     * @param message GCM message received.
     */
    private fun sendNotification(message: String?, title: String?, intent: Intent?) {
        val message = message ?: return
        val title = title ?: return

        intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT)

        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val notificationBuilder: NotificationCompat.Builder

        val userChannel = createNotificationChannel(preferencesWorker.get(userID).toString())
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O && userChannel != null) {
            notificationBuilder = NotificationCompat.Builder(this, userChannel)
                    .setChannelId(userChannel)
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingIntent)
        } else {
            notificationBuilder = NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingIntent)
        }

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build())
    }

    fun createNotificationChannel(userUID: String): String? {

        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.O) {
            return null
        }

        val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // The id of the channel.

        // The user-visible name of the channel.

        // The user-visible description of the channel.
        val description = "Job update notifications"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val mChannel = NotificationChannel(userUID, userUID, importance)

        // Configure the notification channel.
        mChannel.description = description
        mChannel.enableLights(true)

        // Sets the notification light color for notifications posted to this
        // channel, if the device supports this feature.
        mChannel.lightColor = Color.RED
        mChannel.enableVibration(true)
        mNotificationManager.createNotificationChannel(mChannel)

        return userUID
    }
}