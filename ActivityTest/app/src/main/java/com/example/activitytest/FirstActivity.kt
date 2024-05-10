package com.example.activitytest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import com.example.activitytest.databinding.FirstLayoutBinding

class FirstActivity : AppCompatActivity() {
    private lateinit var binding: FirstLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


//        setContentView(R.layout.first_layout)
//        1. findViewById 的写法
//        val Btn = findViewById<Button>(R.id.btn1)
//        Btn.setOnClickListener {
//            val t = Toast.makeText(this, "点击了按钮 1", Toast.LENGTH_SHORT);
//            t.show();
//        }

//        ViewBinding 写法
        binding = FirstLayoutBinding.inflate(layoutInflater)
        binding.btn1.setOnClickListener {
//            showToast("点了按钮一")
            // 显示 Intent
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }


        binding.btn2.setOnClickListener {
            showToast("销毁当前 Activity ")
            finish();
        }
        setContentView(binding.root);
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // 给当前 Activity 创建菜单
        menuInflater.inflate(R.menu.main, menu);
        // return true 表示希望显示出来
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_item -> showToast("add menu item")
            R.id.remove_item -> showToast("remove menu item")
        }
        // return true 表示希望显示出来
        return true
    }

    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}