package com.kiddocode.app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class LessonsActivity : AppCompatActivity() {

    private lateinit var btnBack: ImageButton
    private lateinit var lesson1: CardView
    private lateinit var lesson2: CardView
    private lateinit var lesson3: CardView
    private lateinit var lesson4: CardView
    private lateinit var lesson5: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lessons)

        initViews()
        setupClickListeners()
    }

    private fun initViews() {
        btnBack = findViewById(R.id.btn_back)
        lesson1 = findViewById(R.id.lesson_1)
        lesson2 = findViewById(R.id.lesson_2)
        lesson3 = findViewById(R.id.lesson_3)
        lesson4 = findViewById(R.id.lesson_4)
        lesson5 = findViewById(R.id.lesson_5)
    }

    private fun setupClickListeners() {
        // 返回按钮
        btnBack.setOnClickListener {
            finish()
        }

        // 课程点击
        lesson1.setOnClickListener {
            openLesson(1)
        }
        lesson2.setOnClickListener {
            openLesson(2)
        }
        lesson3.setOnClickListener {
            openLesson(3)
        }
        lesson4.setOnClickListener {
            openLesson(4)
        }
        lesson5.setOnClickListener {
            openLesson(5)
        }
    }

    private fun openLesson(lessonNumber: Int) {
        if (lessonNumber == 1) {
            // 第一课已解锁，可以直接打开
            Toast.makeText(this, "正在打开第${lessonNumber}课...", Toast.LENGTH_SHORT).show()
            // 实际项目中，这里可以跳转到专门的课程详情页面
            // 或者直接跳转到编程页面，让孩子练习特定的内容
            startActivity(Intent(this, BlockCodingActivity::class.java))
        } else {
            // 后面的课程暂时锁定
            Toast.makeText(this, "请先完成前一课解锁这一课哦！📚", Toast.LENGTH_SHORT).show()
        }
    }
}
