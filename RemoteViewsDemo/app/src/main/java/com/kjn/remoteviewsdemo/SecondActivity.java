
package com.kjn.remoteviewsdemo;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RemoteViews;

import static com.kjn.remoteviewsdemo.MainActivity.EXTRA_REMOTE_VIEWS;
import static com.kjn.remoteviewsdemo.MainActivity.REMOTE_ACTION;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void buttonClick2(View view) {
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.layout_simulated_notification);

        remoteViews.setImageViewResource(R.id.icon,R.drawable.icon1);
        remoteViews.setTextViewText(R.id.msg, "msg from process:" + Process.myPid());
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, SecondActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

        remoteViews.setOnClickPendingIntent(R.id.item_holder,pendingIntent);

        Intent intent = new Intent(REMOTE_ACTION);
        intent.putExtra(EXTRA_REMOTE_VIEWS, remoteViews);
        sendBroadcast(intent);
    }
}
