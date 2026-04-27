package com.kiddocode.app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class ProgressActivity : AppCompatActivity() {

    private lateinit var btnBack: ImageButton
    private lateinit var btnContinue: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress)

        initViews()
        setupClickListeners()
    }

    private fun initViews() {
        btnBack = findViewById(R.id.btn_back)
        btnContinue = findViewById(R.id.btn_continue)
    }

    private fun setupClickListeners() {
        // 返回按钮
        btnBack.setOnClickListener {
            finish()
        }

        // 继续学习按钮
        btnContinue.setOnClickListener {
            // 跳转到课程页面
            startActivity(Intent(this, LessonsActivity::class.java))
        }
    }
}
