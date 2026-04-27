package com.kiddocode.app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class GameCenterActivity : AppCompatActivity() {

    private lateinit var btnBack: Button
    private lateinit var cardMathAdventure: CardView
    private lateinit var cardMaze: CardView
    private lateinit var cardMemory: CardView
    private lateinit var cardCollect: CardView
    private lateinit var cardSokoban: CardView
    private lateinit var cardParkour: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_center)

        initViews()
        setupClickListeners()
    }

    private fun initViews() {
        btnBack = findViewById(R.id.btn_back)
        cardMathAdventure = findViewById(R.id.card_math_adventure)
        cardMaze = findViewById(R.id.card_maze)
        cardMemory = findViewById(R.id.card_memory)
        cardCollect = findViewById(R.id.card_collect)
        cardSokoban = findViewById(R.id.card_sokoban)
        cardParkour = findViewById(R.id.card_parkour)
    }

    private fun setupClickListeners() {
        btnBack.setOnClickListener { finish() }
        cardMathAdventure.setOnClickListener { openGame("数学冒险王", "math-adventure.html") }
        cardMaze.setOnClickListener { openGame("迷宫探险", "maze-explorer.html") }
        cardMemory.setOnClickListener { openGame("记忆翻牌", "memory-game.html") }
        cardCollect.setOnClickListener { openGame("接物小游戏", "catch-game.html") }
        cardSokoban.setOnClickListener { openGame("推箱子", "sokoban.html") }
        cardParkour.setOnClickListener { openGame("跑酷大冒险", "parkour.html") }
    }

    private fun openGame(title: String, fileName: String) {
        val intent = Intent(this, WebGameActivity::class.java).apply {
            putExtra(WebGameActivity.EXTRA_GAME_TITLE, title)
            putExtra(WebGameActivity.EXTRA_GAME_URL, "file:///android_asset/$fileName")
        }
        startActivity(intent)
    }
}
