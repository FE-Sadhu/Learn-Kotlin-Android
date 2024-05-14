package com.example.activitytest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.activitytest.databinding.FirstLayoutBinding
import com.example.activitytest.databinding.ThirdLayoutBinding

class ThirdActivity : BaseActivity() {
    private lateinit var binding: ThirdLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.third_layout)
        binding = ThirdLayoutBinding.inflate(layoutInflater)
        binding.btn3.setOnClickListener {
            Log.d("ThirdActivity", "退出所有 Activity")
            ActivityCollector.finishAll()
        }
        setContentView(binding.root)
    }
}