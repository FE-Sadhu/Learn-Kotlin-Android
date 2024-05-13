package com.example.activitytest

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import com.example.activitytest.databinding.FirstLayoutBinding

class FirstActivity : AppCompatActivity() {
    private lateinit var binding: FirstLayoutBinding

    /**
     * 在 Activity 被销毁之前调用 --- 完整生存期-结束
     */
    override fun onDestroy() {
        super.onDestroy()
    }

    /**
     * 在 Activity 第一次被创建时调用。 --- 完整生存期-开始
     * 适合初始化工作，加载布局、绑定事件等。
     */
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
            // 显示 Intent --- 创建 Intent 时明确指明我要启动哪个 Intent
            val data = "Activity 之间传递数据"
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("extra", data);

            // 隐式 Intent --- 创建时只指明启动的 action 和 category
            // 由系统调度给适合处理的 intent-filter Activity 处理
//            val intent = Intent("com.example.activitytest.SEC")
//            intent.addCategory("MyCate")
            startActivity(intent)
        }


        binding.btn2.setOnClickListener {
//            showToast("销毁当前 Activity ")
//            finish();
            /**
             *
             * Intent.ACTION_VIEW 用于启动一个 Activity 来显示指定的数据。
             * 这个 Intent 告诉系统你想要查看某种数据（比如一个网页、一个图片、一个视频等）
             * 然后系统会启动适合查看该数据的应用程序（比如浏览器、图片查看器、视频播放器等）来显示这个数据。
             *
             */
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.baidu.com/");
            startActivity(intent)
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

    /**
     * Activity 由不可见变为可见时调用 --- 可见性生存期-可见
     */
    override fun onStart() {
        super.onStart()
    }

    /**
     * 在 Activity 完全不可见时调用 --- 可见性生存期-不可见
     */
    override fun onStop() {
        super.onStop()
    }

    /**
     * 当 Activity 处于返回栈栈顶，并且处于运行状态时(也就是可以与用户交互) --- 前台生存期-前台
     */
    override fun onResume() {
        super.onResume()
    }

    /**
     * 在系统准备去启动或恢复另一个 Activity 时调用。(此时当前 Activity 是可能可见的，这也是与 onStop 的区别) --- 前台生存期-后台（也就是不处于运行状态）
     */
    override fun onPause() {
        super.onPause()
    }


    /**
     * 在 Activity 由停止状态变为运行状态之前调用，也就是被重新启动了
     */
    override fun onRestart() {
        super.onRestart()
    }

    /**
     * 当 Activity 被系统回收内存时会调用，我们可以存数据，然后在 onCreate 里取出来
     */
    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)

    }
}