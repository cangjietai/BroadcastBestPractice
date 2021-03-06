package com.lisitao.broadcastbestpractice;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


//创建BaseActivity类作为所有活动的父类
public class BaseActivity extends AppCompatActivity {

    private ForceOfflineReceiver receiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityController.addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.lisitao.broadcastbestpractice.FORCE_OFFLINE");
        receiver = new ForceOfflineReceiver();
        registerReceiver(receiver,intentFilter);
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (receiver != null) {
            unregisterReceiver(receiver);
            receiver = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityController.removeActivity(this);
    }

    static class ForceOfflineReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(final Context context, Intent intent) {
           AlertDialog.Builder builder = new AlertDialog.Builder(context);
           builder.setTitle("Warning");
           builder.setMessage("You are forced to be offline. Please try to login again.");
           builder.setCancelable(false);
           builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int which) {
                   ActivityController.finishAll(); //销毁所有活动
                   Intent intent = new Intent(context,LoginActivity.class);
                   context.startActivity(intent);
               }
           });
           builder.show();
        }
    }
}
