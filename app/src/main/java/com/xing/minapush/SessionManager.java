package com.xing.minapush;

import android.media.MediaCas;

import org.apache.mina.core.session.IoSession;

/**
 * 单例
 * Created by Administrator on 2018/9/8.
 */

public class SessionManager {

    private static SessionManager instance;
    private IoSession mSession;

    public SessionManager() {

    }

    public static SessionManager getInstance() {
        if (instance == null) {
            synchronized (SessionManager.class) {
                if (instance == null) {
                    instance = new SessionManager();
                }
            }
        }
        return instance;
    }

    public IoSession getSession() {
        return mSession;
    }

    public void setSession(IoSession session) {
        this.mSession = session;
    }


    public void write(String msg) {
        if (mSession != null) {
            mSession.write(msg);
        }
    }

    public void close() {
        if (mSession != null) {
            mSession.closeOnFlush();
        }
    }

    public void remove() {
        mSession = null;
    }


}
