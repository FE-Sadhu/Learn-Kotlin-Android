package com.example.databasetest

import android.annotation.SuppressLint
import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    @SuppressLint("Range")
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

        val queryData = findViewById<Button>(R.id.query_data)
        queryData.setOnClickListener {
            val db = dbHelper.readableDatabase
            // 第一个参数不用说，当然还是表名，表示我们希望从哪张表中查询数据。
            // 第二个参数用于指定去查询哪几列，如果不指定则默认查询所有列。
            // 第三、第四个参数用于约束查询某一行或某几行的数据，不指定则默认查询所有行的数据。
            // 第五个参数用于指定需要去 group by的列，不指定则表示不对查询结果进行groupby操作。
            // 第六个参数用于对group by之后的数据进行进一步的过滤，不指定则表示不进行过滤。
            // 第七个参数用于指定查询结果的排序方式，不指定则表示使用默认的排序方式。
            val cursor = db.query("Book", null, null, null, null, null, null, null)
            // 将指针移动到第一行
            if (cursor.moveToFirst()) {
                 do {
                     val name = cursor.getString(cursor.getColumnIndex("name"))
                     val author = cursor.getString(cursor.getColumnIndex("author"))
                     val pages = cursor.getInt(cursor.getColumnIndex("pages"))
                     val price = cursor.getDouble(cursor.getColumnIndex("price"))
                     Log.d("MainActivity", "书名 $name")
                     Log.d("MainActivity", "作者 $author")
                     Log.d("MainActivity", "页数 $pages")
                     Log.d("MainActivity", "价格 $price")
                 } while (cursor.moveToNext()) // 一行一行读
            }
            cursor.close()
        }

        val replaceData = findViewById<Button>(R.id.replace_data)
        replaceData.setOnClickListener {
            val db = dbHelper.writableDatabase
            // 开启事务
            db.beginTransaction();
            try {
                db.delete("Book", null, null)
                if (true) {
                    // 手动抛出一个异常，让事务失败
                    throw NullPointerException()
                }
                val values = ContentValues().apply {
                    put("name", "Game of Thrones")
                    put("author", "George Martin")
                    put("pages", 720)
                    put("price", 20.85)
                }
                db.insert("Book", null, values)
                // 事务已经执行成功
                db.setTransactionSuccessful()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                // 结束事务
                db.endTransaction()
            }
        }
    }
}