package com.xing.minapush;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MinaMessageReceiver extends BroadcastReceiver {

    private static final String TAG = "MinaMessageReceiver";
    public static final String MSG = "message";
    public static final String MINA_ACTION = "com.xing.minapush.ACTION_UPDATE";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: ");
        String action = intent.getAction();
        Bundle bundle = intent.getExtras();
        if (MINA_ACTION.equals(action)) {
            if (bundle != null) {
                String message = bundle.getString(MSG);
                showNotification(context, message);
            }
        }
    }


    private void showNotification(Context context, String message) {
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification.Builder(context)
                .setContentTitle("you have a new message")
                .setContentText(message)
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setWhen(System.currentTimeMillis())
                .setPriority(Notification.PRIORITY_MAX)
                .build();
        nm.notify(0x11, notification);
    }
}
