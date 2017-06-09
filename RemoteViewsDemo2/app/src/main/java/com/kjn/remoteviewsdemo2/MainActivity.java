package com.kjn.remoteviewsdemo2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {

    public static final String REMOTE_ACTION = "com.kjn.action_REMOTE";
    public static final String EXTRA_REMOTE_VIEWS = "extra_remoteViews";


    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            RemoteViews remoteViews = intent.getParcelableExtra(EXTRA_REMOTE_VIEWS);
            if (remoteViews != null) {
                updateUI(remoteViews);
            }
        }
    };

    private void updateUI(RemoteViews remoteViews) {
//        int layoutId = getResources().getIdentifier("layout_simulated_notifications", "layout", getPackageName());
//        View view = getLayoutInflater().inflate(layoutId, mLinearLayout,false);
        View view = getLayoutInflater().inflate(R.layout.ff, mLinearLayout,false);
        remoteViews.reapply(this, view);
        mLinearLayout.addView(view);
    }

    private LinearLayout mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mLinearLayout = (LinearLayout) findViewById(R.id.my_layout);
        IntentFilter intentFilter = new IntentFilter(REMOTE_ACTION);
        registerReceiver(mBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcastReceiver);
    }
}

