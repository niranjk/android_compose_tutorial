package com.niranjan.khatri.androidcomposetutorial.concepts.backgroundwork

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.niranjan.khatri.androidcomposetutorial.R

class MyWorker(appContext: Context, workerParams: WorkerParameters): Worker(appContext, workerParams) {

    override fun doWork(): Result {
        // Perform your background work here
        // For example, download a file or process data

        return Result.success()
    }
}

fun scheduleWorkWithConstraints(context: Context) {
    val constraints = Constraints.Builder()
        .setRequiresCharging(true)
        .setRequiredNetworkType(androidx.work.NetworkType.UNMETERED)
        .build()

    val myWorkRequest = OneTimeWorkRequestBuilder<MyWorker>()
        .setConstraints(constraints)
        .build()

    androidx.work.WorkManager.getInstance(context).enqueue(myWorkRequest)
}


class MyMediaProcessingService : Service() {

    private val CHANNEL_ID = "media_processing_channel"
    private val NOTIFICATION_ID = 1

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notification = createNotification()
        startForeground(NOTIFICATION_ID, notification)

        // Start your data synchronization logic here

        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        // Stop data synchronization and release resources
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelName = "Data Synchronization"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, channelName, importance)
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun createNotification(): Notification {
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Data Sync Service")
            .setContentText("Syncing data...")
            .setSmallIcon(R.drawable.ic_boy) // Replace with your icon
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        return builder.build()
    }
}