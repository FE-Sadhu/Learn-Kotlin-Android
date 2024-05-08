package com.example.activitytest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            val t = Toast.makeText(this, "点了按钮一", Toast.LENGTH_SHORT)
            t.show()
        }
        setContentView(binding.root);
    }
}