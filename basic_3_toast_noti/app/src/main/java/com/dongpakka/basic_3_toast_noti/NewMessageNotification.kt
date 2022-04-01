package com.dongpakka.basic_3_toast_noti

import android.annotation.TargetApi
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat

object NewMessageNotification {
    private val NOTIFICATION_TAG = "NewMessage"
    fun notify(context: Context, exampleString: String, number: Int) {
        val res = context.resources

        val picture = BitmapFactory.decodeResource(res, R.mipmap.ic_launcher)
        val title = "제목입니다"
        val text = exampleString
        val builder = NotificationCompat.Builder(context)

            // 알림 시, 진동 또는 플래쉬 등의 설정
            .setDefaults(Notification.DEFAULT_ALL)

            // 시스템 영역의 아이콘, 타이틀, 메세지 내용
            .setSmallIcon(R.drawable.ic_baseline_info_24)
            .setContentTitle(title)
            .setContentText(text)

            // 이 밑에서부터는 모두 옵션이다.
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setLargeIcon(picture)
            .setTicker(exampleString)
            .setNumber(number)
            // Touch 시, 행동(Intent)
            .setContentIntent(
                PendingIntent.getActivity(
                    context, 0,
                    // [TODO] 이곳에 원하는 Intent 만들기
                    // 일반적으로 원하는 Activity를 호출
                    Intent(context, MainActivity::class.java),
                    PendingIntent.FLAG_UPDATE_CURRENT
                )
            )
            // 터치시 자동삭제
            .setAutoCancel(true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {       // 안드로이드 8(오레오부터)
            val nm = context.getSystemService(
                Context.NOTIFICATION_SERVICE) as NotificationManager
            val mChannel = NotificationChannel(
                "MY_NOTI_CHANNEL_ID",
                "MY_NOTI_CHANNEL",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            nm.createNotificationChannel(mChannel)
            builder.setChannelId("MY_NOTI_CHANNEL_ID")
        }
        notify(context, builder.build())
    }
    // Notification 만들기
    private fun notify(context: Context, notification: Notification){
        val nm = context.getSystemService(
            Context.NOTIFICATION_SERVICE
        ) as NotificationManager

            nm.notify(NOTIFICATION_TAG, 0, notification)
    }
    // Notification 삭제하기
    fun cancel(context: Context){
        val nm = context.getSystemService(
            Context.NOTIFICATION_SERVICE
        ) as NotificationManager
            nm.cancel(NOTIFICATION_TAG, 0)
    }
}