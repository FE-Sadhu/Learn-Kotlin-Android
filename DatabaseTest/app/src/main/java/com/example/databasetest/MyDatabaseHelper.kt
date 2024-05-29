package com.example.databasetest

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

/**
 *
 * 继承自 SQLiteOpenHelper。
 *
 * 第二个参数是数据库名，创建数据库时使用的就是这里指定的名称；
 * 第三个参数允许我们在查询数据的时候返回一个自定义的Cursor，一般传入null即可；
 * 第四个参数表示当前数据库的版本号，可用于对数据库进行升级操作。
 *
 */
class MyDatabaseHelper(private val context: Context, name: String, version: Int):
    SQLiteOpenHelper(context, name, null, version) {

        // 创建一个 Book 表
    private val createBook = "create table Book (" +
            " id integer primary key autoincrement," + // integer 表示整型，autoincrement 表示 id 是递增的
            "author text," + // text 表示文本类型
            "price real," + // real 表示浮点型
            "pages integer," +
            "name text)"

    override fun onCreate(db: SQLiteDatabase?) {
        // 重写在此处做创建数据库逻辑
        db!!.execSQL(createBook)
        Toast.makeText(context, "创建成功", Toast.LENGTH_SHORT).show()
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        // 重写在此处做升级数据库逻辑
        TODO("Not yet implemented")
    }
}