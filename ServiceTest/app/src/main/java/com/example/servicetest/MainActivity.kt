package com.example.servicetest

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var DownloadBinder: MyService.DownloadBinder

    /**
     * 用于 Activity 和 Service 建连
     */
    private val connection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            DownloadBinder = p1 as MyService.DownloadBinder;
            DownloadBinder.startDownload()
            DownloadBinder.getProgress()
        }

        /**
         * 只有在 Service 的创建进程崩溃或者被杀掉的调用
         */
        override fun onServiceDisconnected(p0: ComponentName?) {
            Log.d("ServiceConnection", "onServiceDisconnected 执行 ")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startBtn = findViewById<Button>(R.id.start)
        val endBtn = findViewById<Button>(R.id.stop)
        startBtn.setOnClickListener {
            // 启动 service
            val intent = Intent(this, MyService::class.java)
            startService(intent)
        }
        endBtn.setOnClickListener {
            // 关闭 service
            val intent = Intent(this, MyService::class.java)
            stopService(intent)
        }

        val bindBtn = findViewById<Button>(R.id.bind)
        val unbindBtn = findViewById<Button>(R.id.unbind)
        bindBtn.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            // 传入 BIND_AUTO_CREATE 表示在 Activity 和 Service 进行绑定后自动创建 Service。（Service 的 onCreate 执行、onStartCommand 不执行）
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }

        unbindBtn.setOnClickListener {
            unbindService(connection)
        }
    }
}