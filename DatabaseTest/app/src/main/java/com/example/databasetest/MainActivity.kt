package com.example.databasetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.createDb)
        val dbHelper = MyDatabaseHelper(this, "BookStore.db", 1)

        btn.setOnClickListener {
            // 本质是调用 getWritableDatabase 方法
            // 调用会创建或打开一个现有的数据库（如果数据库已存在则直接打开，否则要创建一个新的数据库），
            // 并返回一个可对数据库进行写操作的对象。 （getReadableDatabase 就返回进行读操作的对象）
            dbHelper.writableDatabase
        }
    }
}