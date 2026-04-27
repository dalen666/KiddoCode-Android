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

class PythonBridgeActivity : AppCompatActivity() {

    private val bridgeSections = listOf(
        BridgeSection(
            id = 1,
            title = "单元一：概念桥梁",
            description = "建立Scratch→Python的概念映射",
            icon = "🔗",
            difficulty = "入门",
            duration = "3周",
            features = listOf(
                "概念对应表",
                "基础语法入门",
                "翻译练习"
            )
        ),
        BridgeSection(
            id = 2,
            title = "单元二：Python可视化入门",
            description = "使用Turtle库，保留Scratch思维",
            icon = "🐢",
            difficulty = "入门",
            duration = "4周",
            features = listOf(
                "Turtle库基础",
                "图形绘制",
                "动画效果"
            )
        ),
        BridgeSection(
            id = 3,
            title = "单元三：纯代码编程",
            description = "独立编写Python代码",
            icon = "💻",
            difficulty = "中级",
            duration = "4周",
            features = listOf(
                "数据类型",
                "函数定义",
                "项目实战"
            )
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_python_bridge)

        setupUI()
    }

    private fun setupUI() {
        // 设置标题
        findViewById<TextView>(R.id.title_text).text = "🐍 Scratch → Python 过渡"
        
        // 概念映射快速查看
        setupConceptMapping()
        
        // 过渡单元列表
        setupBridgeSections()
        
        // 快速练习按钮
        setupQuickPractice()
    }

    private fun setupConceptMapping() {
        val mappingContainer = findViewById<LinearLayout>(R.id.concept_mapping_container)
        
        val quickMappingItems = listOf(
            Pair("当绿旗被点击", "if __name__ == \"__main__\":"),
            Pair("移动10步", "forward(10)"),
            Pair("说\"你好\"", "print(\"你好\")"),
            Pair("重复执行10次", "for i in range(10):"),
            Pair("如果...那么", "if condition:")
        )

        for ((scratch, python) in quickMappingItems) {
            val mappingCard = createMappingCard(scratch, python)
            mappingContainer.addView(mappingCard)
        }
    }

    private fun createMappingCard(scratch: String, python: String): View {
        val card = CardView(this)
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(16, 8, 16, 8)
        card.layoutParams = params
        card.radius = 12f
        card.cardElevation = 4f
        card.setContentPadding(16, 16, 16, 16)

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL

        val scratchText = TextView(this)
        scratchText.text = "🟢 Scratch: $scratch"
        scratchText.textSize = 14f
        scratchText.setTypeface(null, android.graphics.Typeface.BOLD)
        layout.addView(scratchText)

        val arrowText = TextView(this)
        arrowText.text = "    ↓"
        arrowText.textSize = 12f
        layout.addView(arrowText)

        val pythonText = TextView(this)
        pythonText.text = "🐍 Python: $python"
        pythonText.textSize = 14f
        layout.addView(pythonText)

        card.addView(layout)
        return card
    }

    private fun setupBridgeSections() {
        val container = findViewById<LinearLayout>(R.id.bridge_sections_container)
        container.removeAllViews()

        for (section in bridgeSections) {
            val sectionView = createSectionCard(section)
            container.addView(sectionView)
        }
    }

    private fun createSectionCard(section: BridgeSection): View {
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
            openSectionDetail(section)
        }

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL

        val titleLayout = LinearLayout(this)
        titleLayout.orientation = LinearLayout.HORIZONTAL

        val iconView = TextView(this)
        iconView.text = section.icon
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
        titleText.text = section.title
        titleText.textSize = 18f
        titleText.setTypeface(null, android.graphics.Typeface.BOLD)
        titleText.setTextColor(resources.getColor(R.color.text_dark))
        titleContainer.addView(titleText)

        val descText = TextView(this)
        descText.text = section.description
        descText.textSize = 14f
        descText.setTextColor(resources.getColor(R.color.text_light))
        titleContainer.addView(descText)

        titleLayout.addView(titleContainer)
        layout.addView(titleLayout)

        val infoLayout = LinearLayout(this)
        infoLayout.orientation = LinearLayout.HORIZONTAL
        infoLayout.setPadding(0, 12, 0, 0)

        val difficultyTag = createTag("难度: ${section.difficulty}")
        infoLayout.addView(difficultyTag)

        val durationTag = createTag("时长: ${section.duration}")
        durationTag.setPadding(16, 8, 16, 8)
        infoLayout.addView(durationTag)

        layout.addView(infoLayout)

        val featuresLabel = TextView(this)
        featuresLabel.text = "✨ 学习内容："
        featuresLabel.textSize = 14f
        featuresLabel.setTypeface(null, android.graphics.Typeface.BOLD)
        featuresLabel.setPadding(0, 16, 0, 8)
        layout.addView(featuresLabel)

        for (feature in section.features) {
            val featureText = TextView(this)
            featureText.text = "   • $feature"
            featureText.textSize = 13f
            featureText.setTextColor(resources.getColor(R.color.text_light))
            featureText.setPadding(0, 4, 0, 4)
            layout.addView(featureText)
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

    private fun setupQuickPractice() {
        val practiceBtn = findViewById<Button>(R.id.quick_practice_btn)
        practiceBtn.setOnClickListener {
            val intent = Intent(this, PracticeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun openSectionDetail(section: BridgeSection) {
        val intent = Intent(this, SectionDetailActivity::class.java)
        intent.putExtra("section_id", section.id)
        intent.putExtra("section_title", section.title)
        startActivity(intent)
    }
}

data class BridgeSection(
    val id: Int,
    val title: String,
    val description: String,
    val icon: String,
    val difficulty: String,
    val duration: String,
    val features: List<String>
)
