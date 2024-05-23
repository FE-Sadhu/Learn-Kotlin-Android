package com.example.broadcasttest

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.broadcasttest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var timeChangeReceiver: TimeChangeReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 注册期望监听的系统广播
        val intentFilter = IntentFilter()
        intentFilter.addAction("android.intent.action.TIME_TICK")
        timeChangeReceiver = TimeChangeReceiver()
        registerReceiver(timeChangeReceiver, intentFilter)


        binding.button.setOnClickListener {
            // 发起标准广播
            val intent = Intent("com.example.broadcasttest.MY_BROADCAST")
            // setPackage 指定接收 intent 的包名，让其变成一个显示广播。
            intent.setPackage(packageName)
            sendBroadcast(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(timeChangeReceiver)
    }

    // 监听广播
    inner class TimeChangeReceiver: BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
//            Toast.makeText(p0, p)

            Log.i("TimeChangeReceiver", p1.toString())
        }
    }
}