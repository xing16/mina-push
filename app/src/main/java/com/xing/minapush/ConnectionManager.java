package com.xing.minapush;

import android.content.Context;
import android.util.Log;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * 负责连接的管理类，建立连接，断开连接
 * <p>
 * Created by Administrator on 2018/9/8.
 */

public class ConnectionManager {

    private Context mContext;
    private ConnectionConfig mConfig;
    private NioSocketConnector mConnector;
    private IoSession mSession;
    private InetSocketAddress mAddress;


    public ConnectionManager(Context context, ConnectionConfig config) {
        mContext = context;
        mConfig = config;
        init();
    }

    private void init() {
        mConnector = new NioSocketConnector();
        mConnector.getFilterChain().addLast("logger", new LoggingFilter());
        mConnector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
        mConnector.getSessionConfig().setReadBufferSize(mConfig.getBufferSize());
        mConnector.setHandler(new ConnectionHandler(mContext));
        mAddress = new InetSocketAddress(mConfig.getIp(), mConfig.getPort());
        mConnector.setDefaultRemoteAddress(mAddress);
    }


    public boolean connect() {
        try {
            ConnectFuture connectFuture = mConnector.connect();
            connectFuture.awaitUninterruptibly();
            mSession = connectFuture.getSession();
            Log.d("deugdebug", "connect: msession = " + mSession);
            SessionManager.getInstance().setSession(mSession);
            return mSession != null;
        } catch (Exception e) {
            return false;
        }
    }

    public void disconnect() {
        mConnector.dispose();
        mConnector = null;
        mAddress = null;
        mSession = null;
    }


}
