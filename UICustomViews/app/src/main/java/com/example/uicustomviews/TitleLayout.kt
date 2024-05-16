package com.example.uicustomviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast

class TitleLayout(context: Context, attrs: AttributeSet): LinearLayout(context, attrs) {
    init {
        LayoutInflater.from(context).inflate(R.layout.title, this);

        val btn = findViewById<Button>(R.id.titleBack);
        btn.setOnClickListener {
            Toast.makeText(context, "你点击了按钮", Toast.LENGTH_LONG).show()
        }
    }


}