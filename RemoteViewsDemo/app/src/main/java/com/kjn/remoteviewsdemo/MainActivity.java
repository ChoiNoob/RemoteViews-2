package com.kjn.remoteviewsdemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
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
        View view = remoteViews.apply(this, mLinearLayout);
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

    public void buttonClick1(View view) {
        Notification notification = new Notification();
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.layout_simulated_notification);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0, new Intent(this,SecondActivity.class),
                PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.item_holder,pendingIntent);
        remoteViews.setImageViewResource(R.id.icon,R.drawable.icon1);
        notification.icon = R.mipmap.ic_launcher;
        notification.tickerText = "hello world";
        notification.when = System.currentTimeMillis();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        notification.contentView = remoteViews;
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(1,notification);
    }
    public void buttonClick(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}
