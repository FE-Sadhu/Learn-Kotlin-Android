package com.example.activitytest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_layout)

        val data = intent.getStringExtra("extra");
        Log.i("SecondActivity", "传递的数据 > $data")
    }
}