package com.example.pranaybansal.workmanagerdemo

import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import androidx.work.Data
import androidx.work.Worker


/**
 * we write our logic inside the worker class's doWork method which runs on a seprate task.
 */

class InputWorker : Worker() {

    var notificationTitile:String? = null
    var notificationMsg:String? = null

    override fun doWork(): Result {
        val data = inputData

        data.let {
            notificationTitile = data.getString("title")
            notificationMsg = data.getString("msg")
            createNotification(notificationTitile,notificationMsg)
        }

        return Result.SUCCESS
    }

    fun createNotification(notificationTitile:String?,notificationMsg:String?){
        val builder = NotificationCompat.Builder(applicationContext)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(notificationTitile)
                .setContentText(notificationMsg)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVibrate(LongArray(0))

        NotificationManagerCompat.from(applicationContext).notify(9999, builder.build())
    }
}