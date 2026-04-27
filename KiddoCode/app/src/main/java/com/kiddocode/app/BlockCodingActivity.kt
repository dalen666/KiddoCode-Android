package com.kiddocode.app

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat

class BlockCodingActivity : AppCompatActivity() {

    private lateinit var btnBack: ImageButton
    private lateinit var btnRun: Button
    private lateinit var btnClear: Button
    private lateinit var workspace: LinearLayout
    private lateinit var character: View
    private lateinit var stageArea: FrameLayout
    
    // 积木按钮
    private lateinit var blockMove: Button
    private lateinit var blockTurn: Button
    private lateinit var blockRepeat: Button
    private lateinit var blockWait: Button
    private lateinit var blockSay: Button

    private val handler = Handler(Looper.getMainLooper())
    private val blockList = mutableListOf<ProgramBlock>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_block_coding)

        initViews()
        setupClickListeners()
    }

    private fun initViews() {
        btnBack = findViewById(R.id.btn_back)
        btnRun = findViewById(R.id.btn_run)
        btnClear = findViewById(R.id.btn_clear)
        workspace = findViewById(R.id.workspace)
        character = findViewById(R.id.character)
        stageArea = findViewById(R.id.stage_area)
        
        blockMove = findViewById(R.id.block_move)
        blockTurn = findViewById(R.id.block_turn)
        blockRepeat = findViewById(R.id.block_repeat)
        blockWait = findViewById(R.id.block_wait)
        blockSay = findViewById(R.id.block_say)
    }

    private fun setupClickListeners() {
        // 返回按钮
        btnBack.setOnClickListener {
            finish()
        }

        // 运行按钮
        btnRun.setOnClickListener {
            runProgram()
        }

        // 清空按钮
        btnClear.setOnClickListener {
            clearWorkspace()
        }

        // 积木按钮 - 添加到工作区
        blockMove.setOnClickListener {
            addBlockToWorkspace(ProgramBlockType.MOVE)
        }
        blockTurn.setOnClickListener {
            addBlockToWorkspace(ProgramBlockType.TURN)
        }
        blockRepeat.setOnClickListener {
            addBlockToWorkspace(ProgramBlockType.REPEAT)
        }
        blockWait.setOnClickListener {
            addBlockToWorkspace(ProgramBlockType.WAIT)
        }
        blockSay.setOnClickListener {
            addBlockToWorkspace(ProgramBlockType.SAY)
        }
    }

    private fun addBlockToWorkspace(type: ProgramBlockType) {
        val blockView = createBlockView(type)
        workspace.addView(blockView)
        
        val block = ProgramBlock(type, blockView)
        blockList.add(block)
        
        Toast.makeText(this, "已添加 ${type.title} 积木!", Toast.LENGTH_SHORT).show()
    }

    private fun createBlockView(type: ProgramBlockType): View {
        val cardView = CardView(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                bottomMargin = 8
            }
            radius = 12f
            cardElevation = 4f
            setCardBackgroundColor(ContextCompat.getColor(this@BlockCodingActivity, type.color))
        }

        val innerLayout = LinearLayout(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            setPadding(16, 16, 16, 16)
        }

        val textView = TextView(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f
            )
            text = "${type.icon} ${type.title}"
            textSize = 16f
            textStyle = android.graphics.Typeface.BOLD
            setTextColor(ContextCompat.getColor(this@BlockCodingActivity, R.color.text_light))
        }

        val removeBtn = Button(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            text = "✕"
            textSize = 14f
            setOnClickListener {
                val index = workspace.indexOfChild(cardView)
                if (index >= 0) {
                    workspace.removeViewAt(index)
                    blockList.removeAt(index)
                    Toast.makeText(this@BlockCodingActivity, "已删除积木!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        innerLayout.addView(textView)
        innerLayout.addView(removeBtn)
        cardView.addView(innerLayout)

        return cardView
    }

    private fun clearWorkspace() {
        workspace.removeAllViews()
        blockList.clear()
        Toast.makeText(this, "工作区已清空!", Toast.LENGTH_SHORT).show()
    }

    private fun runProgram() {
        if (blockList.isEmpty()) {
            Toast.makeText(this, "请先添加一些积木!", Toast.LENGTH_SHORT).show()
            return
        }

        btnRun.isEnabled = false
        Toast.makeText(this, "开始运行程序...", Toast.LENGTH_SHORT).show()

        executeBlocksSequentially(0)
    }

    private fun executeBlocksSequentially(index: Int) {
        if (index >= blockList.size) {
            btnRun.isEnabled = true
            Toast.makeText(this, "程序运行完成！🎉", Toast.LENGTH_SHORT).show()
            return
        }

        val block = blockList[index]
        
        when (block.type) {
            ProgramBlockType.MOVE -> {
                animateMove()
                handler.postDelayed({
                    executeBlocksSequentially(index + 1)
                }, 500)
            }
            ProgramBlockType.TURN -> {
                animateTurn()
                handler.postDelayed({
                    executeBlocksSequentially(index + 1)
                }, 500)
            }
            ProgramBlockType.WAIT -> {
                handler.postDelayed({
                    executeBlocksSequentially(index + 1)
                }, 1000)
            }
            ProgramBlockType.REPEAT -> {
                repeatAnimation()
                handler.postDelayed({
                    executeBlocksSequentially(index + 1)
                }, 800)
            }
            ProgramBlockType.SAY -> {
                showSpeechBubble()
                handler.postDelayed({
                    executeBlocksSequentially(index + 1)
                }, 800)
            }
        }
    }

    private fun animateMove() {
        val currentX = character.x
        val moveDistance = 100f
        
        val animator = ObjectAnimator.ofFloat(
            character,
            "translationX",
            currentX,
            currentX + moveDistance
        ).apply {
            duration = 400
        }
        animator.start()
    }

    private fun animateTurn() {
        val currentRotation = character.rotation
        
        val animator = ObjectAnimator.ofFloat(
            character,
            "rotation",
            currentRotation,
            currentRotation + 90f
        ).apply {
            duration = 400
        }
        animator.start()
    }

    private fun repeatAnimation() {
        val animator1 = ObjectAnimator.ofFloat(character, "scaleX", 1f, 1.2f, 1f)
        val animator2 = ObjectAnimator.ofFloat(character, "scaleY", 1f, 1.2f, 1f)
        
        val set = AnimatorSet().apply {
            playTogether(animator1, animator2)
            duration = 400
        }
        set.start()
    }

    private fun showSpeechBubble() {
        Toast.makeText(this, "Hello, World! 👋", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }

    enum class ProgramBlockType(
        val title: String,
        val icon: String,
        val color: Int
    ) {
        MOVE("移动", "➡️", R.color.block_motion),
        TURN("旋转", "🔄", R.color.block_looks),
        REPEAT("重复", "🔁", R.color.block_control),
        WAIT("等待", "⏱️", R.color.block_events),
        SAY("说话", "💬", R.color.block_sound)
    }

    data class ProgramBlock(
        val type: ProgramBlockType,
        val view: View
    )
}
