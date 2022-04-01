package com.dongpakka.basic_3_toast_noti

import android.annotation.TargetApi
import android.app.Notification
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
                    PendingIntent.FLAG_MUTABLE
                )
            )
            // 터치시 자동삭제
            .setAutoCancel(true)
        notify(context, builder.build())
    }
    // Notification 만들기
    @TargetApi(Build.VERSION_CODES.ECLAIR)
    private fun notify(context: Context, notification: Notification){
        val nm = context.getSystemService(
            Context.NOTIFICATION_SERVICE
        ) as NotificationManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
            nm.notify(NOTIFICATION_TAG, 0, notification)
        } else {
            nm.notify(NOTIFICATION_TAG.hashCode(), notification)
        }
    }
    // Notification 삭제하기
    @TargetApi(Build.VERSION_CODES.ECLAIR)
    fun cancel(context: Context){
        val nm = context.getSystemService(
            Context.NOTIFICATION_SERVICE
        ) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
            nm.cancel(NOTIFICATION_TAG, 0)
        }else {
            nm.cancel(NOTIFICATION_TAG.hashCode())
        }
    }
}