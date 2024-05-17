package com.example.fragmentbestpractice

import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.StringBuilder

class NewsTitleFragment: Fragment() {
    private var isTwoPane = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_title_frag, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isTwoPane = activity?.findViewById<View>(R.id.newsContentLayout) != null

        val layoutManager = LinearLayoutManager(activity)
        val newsTitleRecyclerView = view?.findViewById<RecyclerView>(R.id.newsTitleRecyclerView)
        newsTitleRecyclerView?.layoutManager = layoutManager;
        val adapter = NewsAdapter(getNews())
        newsTitleRecyclerView?.adapter = adapter
    }

    private fun getNews(): List<News> {
        val newsList = ArrayList<News>()
        for (i in 1..50) {
            val news = News("这是新闻标题：$i", getRandomLengthString("这是内容 $i."))
            newsList.add(news)
        }
        return newsList
    }

    private fun getRandomLengthString(string: String): String {
        val n = (1..20).random();
        val builder = StringBuilder();
        repeat(n) {
            builder.append(string)
        }
        return builder.toString()
    }

    inner class NewsAdapter(val newsList: List<News>): RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val newsTitle = view.findViewById<TextView>(R.id.newsTitle)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)

            val holder = ViewHolder(view)

            holder.itemView.setOnClickListener {
                val news = newsList[holder.adapterPosition]
                if (isTwoPane) {
                    val fragment = activity?.supportFragmentManager?.findFragmentById(R.id.newsContentFrag) as NewsContentFragment
                    fragment.refresh(news.title, news.content)
                } else {
                    NewsContentActivity.actionStart(parent.context, news.title, news.content)
                }
            }

            return holder
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val news = newsList[position]
            holder.newsTitle.text = news.title
        }

        override fun getItemCount(): Int {
            return newsList.size
        }
    }
}