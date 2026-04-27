package com.kiddocode.app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNav: BottomNavigationView
    private lateinit var cardCoding: CardView
    private lateinit var cardLessons: CardView
    private lateinit var cardGames: CardView
    private lateinit var cardProgress: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 初始化视图
        initViews()
        
        // 设置点击事件
        setupClickListeners()
        
        // 设置底部导航
        setupBottomNav()
    }

    private fun initViews() {
        bottomNav = findViewById(R.id.bottom_nav)
        cardCoding = findViewById(R.id.card_coding)
        cardLessons = findViewById(R.id.card_lessons)
        cardGames = findViewById(R.id.card_games)
        cardProgress = findViewById(R.id.card_progress)
    }

    private fun setupClickListeners() {
        // 编程卡片点击
        cardCoding.setOnClickListener {
            startActivity(Intent(this, BlockCodingActivity::class.java))
        }

        // 课程卡片点击
        cardLessons.setOnClickListener {
            startActivity(Intent(this, LessonsActivity::class.java))
        }

        // 游戏卡片点击
        cardGames.setOnClickListener {
            startActivity(Intent(this, GameCenterActivity::class.java))
        }

        // 进度卡片点击
        cardProgress.setOnClickListener {
            startActivity(Intent(this, ProgressActivity::class.java))
        }
    }

    private fun setupBottomNav() {
        bottomNav.selectedItemId = R.id.nav_home
        
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    // 已经在首页了
                    true
                }
                R.id.nav_coding -> {
                    startActivity(Intent(this, BlockCodingActivity::class.java))
                    true
                }
                R.id.nav_lessons -> {
                    startActivity(Intent(this, LessonsActivity::class.java))
                    true
                }
                R.id.nav_progress -> {
                    startActivity(Intent(this, ProgressActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }

    override fun onResume() {
        super.onResume()
        bottomNav.selectedItemId = R.id.nav_home
    }
}
