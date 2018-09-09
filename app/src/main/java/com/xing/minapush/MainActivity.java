package com.xing.minapush;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.nio.file.FileAlreadyExistsException;

public class MainActivity extends AppCompatActivity {

    private MinaMessageReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerBroadcast();
    }

    private void registerBroadcast() {
        receiver = new MinaMessageReceiver();
        IntentFilter filter = new IntentFilter(MinaMessageReceiver.MINA_ACTION);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver,filter);
    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn_connect_server:
                startService();
                break;
            case R.id.btn_send_message:
                sendMessage();
                break;
        }
    }

    private void sendMessage() {
        SessionManager.getInstance().write("111");

    }

    private void startService() {
        startService(new Intent(this, MinaConnectionService.class));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }
}
