package com.example.filepersistencetest

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val editText = findViewById<EditText>(R.id.editText)
        val inputText = load()
        if (inputText.isNotEmpty()) {
            editText.setText(inputText)
            editText.setSelection(inputText.length)
            Toast.makeText(this, "Restoring success", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        val editText = findViewById<EditText>(R.id.editText)
        val inputText = editText.text.toString()
        save(inputText)
    }

    /**
     * 文件存储的写入
     */
    private fun save(inputText: String) {
        try {
            // 这里通过 openFileOutput() 方法能够得到一个 FileOutputStream 对象，
            val output = openFileOutput("Sadhu_Text", Context.MODE_PRIVATE)
            // 然后借助它构建出一个 OutputStreamWriter 对象，
            // 接着再使用 OutputStreamWriter 构建出一个 BufferedWriter 对象，
            // 这样你就可以通过 BufferedWriter 将文本内容写入文件中了。
            val writer = BufferedWriter(OutputStreamWriter(output))
            // use 函数是 Kotlin 提供的一个内置扩展函数。
            // 它会保证在 Lambda 表达式中的代码全部执行完之后自动将外层的流关闭，
            // 这样就不需要我们再编写一个 finally 语句，手动去关闭流了，是一个非常好用的扩展函数
            writer.use {
                it.write(inputText)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    /**
     * 文件存储的读取
     */
    private fun load(): String {
        val content = StringBuilder();
        try {
            val input = openFileInput("Sadhu_Text")
            val reader = BufferedReader(InputStreamReader(input))
            reader.use {
                reader.forEachLine {
                    Log.i("MainActivity", "读的每一行: $it")
                    content.append(it)
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return content.toString()
    }
}
