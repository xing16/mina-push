package com.xing.minapush;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.HandlerThread;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * 在后台服务中开启线程，在线程中处理建立连接的操作，因为建立连接时耗时操作。
 * Created by Administrator on 2018/9/8.
 */

public class MinaConnectionService extends Service {

    private ConnectionManager connectionManager;
    private ConnectionThread connectionThread;
    private Context mContext;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        connectionThread = new ConnectionThread("mina-connection-thread");
        connectionThread.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }


    public class ConnectionThread extends HandlerThread {

        public ConnectionThread(String name) {
            super(name);
            ConnectionConfig config = new ConnectionConfig.Builder()
                    .setIp("192.168.61.107")
                    .setPort(9223)
                    .setConnectionTimeout(10000)
                    .setBufferSize(1024 * 3)
                    .build();
            connectionManager = new ConnectionManager(mContext, config);
        }

        @Override
        protected void onLooperPrepared() {
            super.onLooperPrepared();
            while (true) {
                if (connectionManager.connect()) {
                    break;
                }

                // 建立连接失败，每隔 2 秒重新建立连接
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (connectionManager != null) {
            connectionManager.disconnect();
        }
    }

}
