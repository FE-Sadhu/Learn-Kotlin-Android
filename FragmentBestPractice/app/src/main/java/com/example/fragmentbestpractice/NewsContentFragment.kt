package com.example.fragmentbestpractice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment

class NewsContentFragment: Fragment() {

    private lateinit var binding: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = inflater.inflate(R.layout.news_content_frag, container, false)
        return binding;
    }

    fun refresh(title: String, content: String) {
        val contentLayout = binding.findViewById<LinearLayout>(R.id.contentLayout)
        contentLayout.visibility = View.VISIBLE

        val newsTitle = binding.findViewById<TextView>(R.id.newsTitle)
        newsTitle.text = title;

        val newsContent = binding.findViewById<TextView>(R.id.newsContent);
        newsContent.text = content;
    }
}