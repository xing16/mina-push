package com.xing.minapush;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * 建立连接后的回调
 * Created by Administrator on 2018/9/8.
 */

public class ConnectionHandler extends IoHandlerAdapter {

    private static final String TAG = "ConnectionHandler";
    private Context mContext;

    public ConnectionHandler(Context context) {
        this.mContext = context;
    }

    @Override
    public void sessionCreated(IoSession ioSession) throws Exception {
        Log.d(TAG, "sessionCreated: ");

    }

    @Override
    public void sessionOpened(IoSession ioSession) throws Exception {
        Log.d(TAG, "sessionOpened: ");
    }

    @Override
    public void sessionClosed(IoSession ioSession) throws Exception {
        Log.d(TAG, "sessionClosed: ");
    }

    @Override
    public void sessionIdle(IoSession ioSession, IdleStatus idleStatus) throws Exception {
        Log.d(TAG, "sessionIdle: ");
    }

    @Override
    public void exceptionCaught(IoSession ioSession, Throwable throwable) throws Exception {
        Log.d(TAG, "exceptionCaught: ");
        Log.d(TAG, "exceptionCaught: -=======" + throwable.getMessage());
    }

    @Override
    public void messageReceived(IoSession ioSession, Object o) throws Exception {
        Log.d(TAG, "messageReceived: ");
        Log.d(TAG, "messageReceived: thread = " + Thread.currentThread().getName());
        // 发送本地广播，用于更新 UI
        sendLocalBroadcast(o);
    }

    private void sendLocalBroadcast(Object o) {
        Intent intent = new Intent();
        intent.setAction(MinaMessageReceiver.MINA_ACTION);
        Bundle bundle = new Bundle();
        bundle.putString(MinaMessageReceiver.MSG, o.toString());
        intent.putExtras(bundle);
        LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
    }


    @Override
    public void messageSent(IoSession ioSession, Object o) throws Exception {
        Log.d(TAG, "messageSent: thread = " + Thread.currentThread().getName());
    }

    @Override
    public void inputClosed(IoSession ioSession) throws Exception {
        Log.d(TAG, "inputClosed: ");
    }
}
