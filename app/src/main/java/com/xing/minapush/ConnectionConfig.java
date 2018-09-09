package com.xing.minapush;

/**
 * 单例
 * Created by Administrator on 2018/9/8.
 */

public class ConnectionConfig {

    private String ip;
    private int port;
    private int connectionTimeout;
    private int bufferSize;

    public static class Builder {
        private String ip;
        private int port;
        private int connectionTimeout;
        private int bufferSize;

        public Builder() {

        }

        public Builder setIp(String ip) {
            this.ip = ip;
            return this;
        }

        public Builder setPort(int port) {
            this.port = port;
            return this;
        }

        public Builder setConnectionTimeout(int timeout) {
            this.connectionTimeout = timeout;
            return this;
        }

        public Builder setBufferSize(int bufferSize) {
            this.bufferSize = bufferSize;
            return this;
        }

        public ConnectionConfig build() {
            ConnectionConfig config = new ConnectionConfig();
            config.ip = ip;
            config.port = port;
            config.connectionTimeout = connectionTimeout;
            config.bufferSize = bufferSize;
            return config;
        }
    }


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }
}
