package com.kiddocode.app

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import android.view.View
import android.content.Intent

class ScratchLearningActivity : AppCompatActivity() {

    private val scratchUnits = listOf(
        UnitData(
            id = 1,
            title = "单元一：Scratch基础入门",
            description = "认识Scratch，学习角色与背景",
            icon = "🎯",
            difficulty = "入门",
            duration = "4周",
            lessons = listOf(
                "认识Scratch界面",
                "角色创建与控制",
                "背景设计",
                "事件处理机制"
            ),
            projects = listOf("会动的小猫", "我的第一个动画")
        ),
        UnitData(
            id = 2,
            title = "单元二：运动与外观编程",
            description = "精确控制角色运动",
            icon = "🏃",
            difficulty = "入门",
            duration = "4周",
            lessons = listOf(
                "运动编程",
                "外观效果",
                "广播与接收"
            ),
            projects = listOf("会移动的汽车", "交互动画")
        ),
        UnitData(
            id = 3,
            title = "单元三：变量与控制流",
            description = "学习变量、循环、条件",
            icon = "🔄",
            difficulty = "中级",
            duration = "5周",
            lessons = listOf(
                "变量与数据管理",
                "循环结构",
                "条件判断"
            ),
            projects = listOf("计分系统", "猜数字游戏")
        ),
        UnitData(
            id = 4,
            title = "单元四：函数与模块化编程",
            description = "掌握自制积木与项目架构",
            icon = "🧩",
            difficulty = "高级",
            duration = "5周",
            lessons = listOf(
                "自制积木",
                "参数传递",
                "项目架构"
            ),
            projects = listOf("多关卡游戏", "原创综合项目")
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scratch_learning)

        setupUI()
    }

    private fun setupUI() {
        val container = findViewById<LinearLayout>(R.id.units_container)
        container.removeAllViews()

        for (unit in scratchUnits) {
            val unitView = createUnitCard(unit)
            container.addView(unitView)
        }
    }

    private fun createUnitCard(unit: UnitData): View {
        val card = CardView(this)
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(16, 8, 16, 8)
        card.layoutParams = params
        card.radius = 16f
        card.cardElevation = 8f
        card.setContentPadding(24, 24, 24, 24)
        card.isClickable = true
        card.setOnClickListener {
            openUnitDetail(unit)
        }

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL

        val titleLayout = LinearLayout(this)
        titleLayout.orientation = LinearLayout.HORIZONTAL

        val iconView = TextView(this)
        iconView.text = unit.icon
        iconView.textSize = 32f
        val iconParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        iconParams.setMargins(0, 0, 16, 0)
        iconView.layoutParams = iconParams
        titleLayout.addView(iconView)

        val titleContainer = LinearLayout(this)
        titleContainer.orientation = LinearLayout.VERTICAL

        val titleText = TextView(this)
        titleText.text = unit.title
        titleText.textSize = 18f
        titleText.setTypeface(null, android.graphics.Typeface.BOLD)
        titleText.setTextColor(resources.getColor(R.color.text_dark))
        titleContainer.addView(titleText)

        val descText = TextView(this)
        descText.text = unit.description
        descText.textSize = 14f
        descText.setTextColor(resources.getColor(R.color.text_light))
        titleContainer.addView(descText)

        titleLayout.addView(titleContainer)
        layout.addView(titleLayout)

        val infoLayout = LinearLayout(this)
        infoLayout.orientation = LinearLayout.HORIZONTAL
        infoLayout.setPadding(0, 12, 0, 0)

        val difficultyTag = createTag("难度: ${unit.difficulty}")
        infoLayout.addView(difficultyTag)

        val durationTag = createTag("时长: ${unit.duration}")
        durationTag.setPadding(16, 8, 16, 8)
        infoLayout.addView(durationTag)

        layout.addView(infoLayout)

        val lessonsLabel = TextView(this)
        lessonsLabel.text = "📚 课程内容："
        lessonsLabel.textSize = 14f
        lessonsLabel.setTypeface(null, android.graphics.Typeface.BOLD)
        lessonsLabel.setPadding(0, 16, 0, 8)
        layout.addView(lessonsLabel)

        for (lesson in unit.lessons) {
            val lessonText = TextView(this)
            lessonText.text = "   • $lesson"
            lessonText.textSize = 13f
            lessonText.setTextColor(resources.getColor(R.color.text_light))
            lessonText.setPadding(0, 4, 0, 4)
            layout.addView(lessonText)
        }

        val projectsLabel = TextView(this)
        projectsLabel.text = "🎮 实践项目："
        projectsLabel.textSize = 14f
        projectsLabel.setTypeface(null, android.graphics.Typeface.BOLD)
        projectsLabel.setPadding(0, 12, 0, 8)
        layout.addView(projectsLabel)

        for (project in unit.projects) {
            val projectText = TextView(this)
            projectText.text = "   ✨ $project"
            projectText.textSize = 13f
            projectText.setTextColor(resources.getColor(R.color.text_light))
            projectText.setPadding(0, 4, 0, 4)
            layout.addView(projectText)
        }

        card.addView(layout)
        return card
    }

    private fun createTag(text: String): View {
        val tag = TextView(this)
        tag.text = text
        tag.textSize = 12f
        tag.setPadding(16, 8, 16, 8)
        tag.setBackgroundResource(R.drawable.tag_background)
        tag.setTextColor(resources.getColor(R.color.text_dark))
        return tag
    }

    private fun openUnitDetail(unit: UnitData) {
        val intent = Intent(this, UnitDetailActivity::class.java)
        intent.putExtra("unit_id", unit.id)
        intent.putExtra("unit_title", unit.title)
        startActivity(intent)
    }
}

data class UnitData(
    val id: Int,
    val title: String,
    val description: String,
    val icon: String,
    val difficulty: String,
    val duration: String,
    val lessons: List<String>,
    val projects: List<String>
)
