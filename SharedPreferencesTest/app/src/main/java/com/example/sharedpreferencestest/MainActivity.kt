package com.example.sharedpreferencestest

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val saveBtn = findViewById<Button>(R.id.saveButton)

        saveBtn.setOnClickListener {
            // SharedPreferences 存的是 xml 文件
            val editor = getSharedPreferences("sadhu_shared_pref", Context.MODE_PRIVATE).edit()
            editor.putString("name", "Sadhu")
            editor.putInt("age", 26)
            editor.putBoolean("married", true)
            editor.apply()
        }

        val restoreBtn = findViewById<Button>(R.id.getButton)

        restoreBtn.setOnClickListener {
            val pref = getSharedPreferences("sadhu_shared_pref", Context.MODE_PRIVATE)
            // 第二个参数是默认值，即表示当传入的键找不到对应的值时会以什么样的默认值进行返回。
            val name = pref.getString("name", "")
            val age = pref.getInt("age", -1)
            val married = pref.getBoolean("married", false);

            Log.i("SharedPreferences", " name $name age $age married $married")

        }
    }
}