package com.example.servicetest

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import kotlin.concurrent.thread

class MyService : Service() {
    private val TAG = "MyService"
    private val mBinder: Binder = DownloadBinder()

    class DownloadBinder: Binder() {
        private val TAG = "DownloadBinder"

        fun startDownload() {
            Log.d(TAG, "startDownload: ")
        }

        fun getProgress(): Int {
            Log.d(TAG, "getProgress execute ")
            return 0
        }
    }

    /**
     * 当 Activity 与 Service 建立 connection 后调用
     */
    override fun onBind(intent: Intent): IBinder {
        return mBinder
    }

    /**
     * Service 创建的时候调用
     */
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate 执行 ")
        // 在这里创建 notify push ，最后调用 startForeground 方法将其变为前台 service，其就会在后台一直保活了。
    }

    /**
     * Service 启动的时候调用
     */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand: 执行")
        // 1. 因为 service 运行在主线程，所以执行耗时任务会阻塞 UI，所以可以开在一个子线程里执行
        // 2. 不想写如下 thread 语法和 stopSelf 方法，可以创建 intentService ，在其重载的 onHandleIntent 里处理具体的逻辑也是异步的。
        thread {
            // 处理具体的逻辑

            // 执行完具体的逻辑后，销毁自身
            stopSelf()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    /**
     * Service 销毁的时候调用
     */
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: 执行")
    }
}