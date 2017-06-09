# RemoteViews

RemoteViews 的跨进程使用方法：



![](https://github.com/KonngGN/RemoteViews/blob/master/GIF.gif)


下载两个文件，分别打开并运行接受者和发送者


同一个 app 下或者同一个 app 不同进程使用更新方法：

```
 View view = remoteViews.apply(this, mLinearLayout);
 mLinearLayout.addView(view);

```

不同 app 下使用方法：

只需要将接收到的 remoteViews 添加到当前布局中即可（资源 id 没有要求必须一样）

```
//资源 ID 命名，两种方式均可
//        int layoutId = getResources().getIdentifier("layout_simulated_notifications", "layout", getPackageName());
//        View view = getLayoutInflater().inflate(layoutId, mLinearLayout,false);
        View view = getLayoutInflater().inflate(R.layout.ff, mLinearLayout,false);
        remoteViews.reapply(this, view);
        mLinearLayout.addView(view);
```
