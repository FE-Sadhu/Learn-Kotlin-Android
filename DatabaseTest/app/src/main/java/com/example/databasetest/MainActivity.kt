package com.example.databasetest

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.createDb)
        val dbHelper = MyDatabaseHelper(this, "BookStore.db", 2)

        btn.setOnClickListener {
            // 本质是调用 getWritableDatabase 方法
            // 调用会创建或打开一个现有的数据库（如果数据库已存在则直接打开，否则要创建一个新的数据库），
            // 并返回一个可对数据库进行写操作的对象。 （getReadableDatabase 就返回进行读操作的对象）
            dbHelper.writableDatabase
        }

        val addData = findViewById<Button>(R.id.add_data)
        addData.setOnClickListener {
            val db = dbHelper.writableDatabase
            val value = ContentValues().apply {
                put("name", "Sadhu 的幸福之旅")
                put("author", "Sadhu")
                put("pages", 666)
                put("price", 66.66)
            }
            // insert 是往表里增加数据
            // 第二个参数用于在未指定添加数据的情况下给某些可为空的列自动赋值 NULL，
            // 一般我们用不到这个功能，直接传入 null 即可
            db.insert("Book", null, value)

            val value1 = ContentValues().apply {
                put("name", "技术为本")
                put("author", "小捞捞")
                put("pages", 888)
                put("price", 88.88)
            }
            db.insert("Book", null, value1)
        }

        val updateData = findViewById<Button>(R.id.update_data)
        updateData.setOnClickListener {
            val db = dbHelper.writableDatabase
            val value = ContentValues().apply {
                put("price", 666.66)
            }
            db.update("Book", value, "id=?", arrayOf("1"))
            val value1 = ContentValues().apply {
                put("name", "小捞捞的幸福之旅")
            }
            db.update("Book", value1, "name=?", arrayOf("技术为本"))
        }

        val delData = findViewById<Button>(R.id.dele_data);
        delData.setOnClickListener {
            val db = dbHelper.writableDatabase
            // 删除 id 大于 2 的数据
            db.delete("Book", "id > ?", arrayOf("2"))
        }
    }
}