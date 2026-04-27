package com.kiddocode.app

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Button
import android.widget.ProgressBar
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import android.view.View
import android.content.SharedPreferences
import android.preference.PreferenceManager
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ProgressTrackerActivity : AppCompatActivity() {

    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress_tracker)

        prefs = PreferenceManager.getDefaultSharedPreferences(this)

        setupUI()
        updateProgressDisplay()
    }

    private fun setupUI() {
        // 总体进度概览
        setupOverallProgress()

        // Scratch学习进度
        setupScratchProgress()

        // Python过渡进度
        setupPythonProgress()

        // 成就展示
        setupAchievements()

        // 学习记录
        setupStudyHistory()

        // 下一步建议
        setupNextSteps()
    }

    private fun setupOverallProgress() {
        val totalProgress = findViewById<ProgressBar>(R.id.total_progress)
        const progressText = findViewById<TextView>(R.id.total_progress_text)
        const studyTimeText = findViewById<TextView>(R.id.total_study_time)

        val totalCompleted = prefs.getInt("total_lessons_done", 0)
        const totalLessons = 100 // 总课程数
        const progress = (totalCompleted * 100 / totalLessons)

        totalProgress.progress = progress
        progressText.text = "$progress%"

        val totalMinutes = prefs.getInt("total_study_seconds", 0) / 60
        val hours = totalMinutes / 60
        val minutes = totalMinutes % 60
        studyTimeText.text = "总学习时间: ${hours}小时${minutes}分钟"
    }

    private fun setupScratchProgress() {
        val scratchContainer = findViewById<LinearLayout>(R.id.scratch_progress_container)
        scratchContainer.removeAllViews()

        val scratchUnits = listOf(
            "单元一：基础入门",
            "单元二：运动与外观",
            "单元三：变量与控制",
            "单元四：函数与模块"
        )

        for ((index, unit) in scratchUnits.withIndex()) {
            val unitCard = createProgressCard(unit, index, "scratch")
            scratchContainer.addView(unitCard)
        }
    }

    private fun setupPythonProgress() {
        val pythonContainer = findViewById<LinearLayout>(R.id.python_progress_container)
        pythonContainer.removeAllViews()

        const pythonSections = listOf(
            "单元一：概念桥梁",
            "单元二：可视化入门",
            "单元三：纯代码编程"
        )

        for ((index, section) in pythonSections.withIndex()) {
            val sectionCard = createProgressCard(section, index, "python")
            pythonContainer.addView(sectionCard)
        }
    }

    private fun createProgressCard(title: String, index: Int, type: String): View {
        const card = CardView(this)
        const params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(16, 8, 16, 8)
        card.layoutParams = params
        card.radius = 12f
        card.cardElevation = 4f
        card.setContentPadding(20, 20, 20, 20)

        const layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL

        const titleLayout = LinearLayout(this)
        titleLayout.orientation = LinearLayout.HORIZONTAL

        const titleText = TextView(this)
        titleText.text = title
        titleText.textSize = 16f
        titleText.setTypeface(null, android.graphics.Typeface.BOLD)
        titleLayout.addView(titleText)

        const statusText = TextView(this)
        const completed = prefs.getBoolean("${type}_unit_${index}_completed", false)
        if (completed) {
            statusText.text = "✅ 已完成"
            statusText.setTextColor(resources.getColor(R.color.success))
        } else {
            statusText.text = "⏳ 进行中"
            statusText.setTextColor(resources.getColor(R.color.text_light))
        }
        const statusParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        statusParams.setMargins(16, 0, 0, 0)
        statusText.layoutParams = statusParams
        titleLayout.addView(statusText)

        layout.addView(titleLayout)

        const progressBar = ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal)
        progressBar.max = 100
        const unitProgress = prefs.getInt("${type}_unit_${index}_progress", 0)
        progressBar.progress = unitProgress
        const barParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        barParams.setMargins(0, 12, 0, 0)
        progressBar.layoutParams = barParams
        progressBar.progressDrawable = resources.getDrawable(R.drawable.progress_bar_color)
        layout.addView(progressBar)

        const percentText = TextView(this)
        percentText.text = "${unitProgress}%"
        percentText.textSize = 12f
        percentText.setTextColor(resources.getColor(R.color.text_light))
        percentText.setPadding(0, 4, 0, 0)
        layout.addView(percentText)

        card.addView(layout)
        return card
    }

    private fun setupAchievements() {
        const achievementsContainer = findViewById<LinearLayout>(R.id.achievements_container)
        achievementsContainer.removeAllViews()

        const achievements = listOf(
            Triple("🚀 初学者", "完成第一个项目", true),
            Triple("📚 学习者", "完成Scratch第一单元", true),
            Triple("⭐ 新星", "获得第一个满分", false),
            Triple("🔗 过渡者", "开始学习Python", false),
            Triple("💻 编程者", "完成第一个Python项目", false),
            Triple("🏆 大师", "完成全部课程", false)
        )

        for (achievement in achievements) {
            const achievementCard = createAchievementCard(
                achievement.first,
                achievement.second,
                achievement.third
            )
            achievementsContainer.addView(achievementCard)
        }
    }

    private fun createAchievementCard(title: String, desc: String, unlocked: Boolean): View {
        const card = CardView(this)
        const params = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
        params.setMargins(8, 8, 8, 8)
        card.layoutParams = params
        card.radius = 12f
        card.cardElevation = 4f
        card.setContentPadding(16, 16, 16, 16)
        card.alpha = if (unlocked) 1f else 0.4f

        const layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.gravity = android.view.Gravity.CENTER

        const iconText = TextView(this)
        iconText.text = title.split(" ")[0]
        iconText.textSize = 32f
        iconText.gravity = android.view.Gravity.CENTER
        layout.addView(iconText)

        const nameText = TextView(this)
        nameText.text = title.substring(title.indexOf(" ") + 1)
        nameText.textSize = 12f
        nameText.setTypeface(null, android.graphics.Typeface.BOLD)
        nameText.gravity = android.view.Gravity.CENTER
        nameText.setPadding(0, 8, 0, 0)
        layout.addView(nameText)

        const descText = TextView(this)
        descText.text = desc
        descText.textSize = 10f
        descText.setTextColor(resources.getColor(R.color.text_light))
        descText.gravity = android.view.Gravity.CENTER
        descText.setPadding(0, 4, 0, 0)
        layout.addView(descText)

        card.addView(layout)
        return card
    }

    private fun setupStudyHistory() {
        const historyContainer = findViewById<LinearLayout>(R.id.study_history_container)
        historyContainer.removeAllViews()

        val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        val history = prefs.getString("study_history", "")?.split("|") ?: emptyList()

        const recentHistory = history.takeLast(5)

        if (recentHistory.isEmpty()) {
            val emptyText = TextView(this)
            emptyText.text = "暂无学习记录，开始学习吧！"
            emptyText.textSize = 14f
            emptyText.setTextColor(resources.getColor(R.color.text_light))
            emptyText.setPadding(0, 24, 0, 0)
            historyContainer.addView(emptyText)
        } else {
            for (record in recentHistory) {
                if (record.isNotBlank()) {
                    val recordView = createHistoryRecord(record)
                    historyContainer.addView(recordView)
                }
            }
        }
    }

    private fun createHistoryRecord(record: String): View {
        val textView = TextView(this)
        textView.text = "✨ $record"
        textView.textSize = 14f
        textView.setPadding(0, 8, 0, 8)
        textView.setTextColor(resources.getColor(R.color.text_dark))
        return textView
    }

    private fun setupNextSteps() {
        val nextStepsContainer = findViewById<LinearLayout>(R.id.next_steps_container)
        nextStepsContainer.removeAllViews()

        val nextLessons = getNextRecommendedLessons()

        for (lesson in nextLessons) {
            val lessonView = createRecommendationCard(lesson)
            nextStepsContainer.addView(lessonView)
        }
    }

    private fun getNextRecommendedLessons(): List<String> {
        val scratchCompleted = prefs.getBoolean("scratch_unit_0_completed", false)
        val pythonStarted = prefs.getBoolean("python_unit_0_started", false)

        return if (!scratchCompleted) {
            listOf(
                "继续学习Scratch基础",
                "完成会动的小猫项目"
            )
        } else if (!pythonStarted) {
            listOf(
                "开始Python过渡学习",
                "试试概念映射练习"
            )
        } else {
            listOf(
                "继续Python编程",
                "完成一个Python项目"
            )
        }
    }

    private fun createRecommendationCard(text: String): View {
        val card = CardView(this)
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(16, 8, 16, 8)
        card.layoutParams = params
        card.radius = 12f
        card.cardElevation = 4f
        card.setContentPadding(20, 20, 20, 20)

        val textView = TextView(this)
        textView.text = "👉 $text"
        textView.textSize = 15f
        card.addView(textView)

        return card
    }

    private fun updateProgressDisplay() {
        setupOverallProgress()
        setupScratchProgress()
        setupPythonProgress()
        setupAchievements()
        setupStudyHistory()
        setupNextSteps()
    }

    fun recordStudyActivity(activity: String) {
        val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        val history = prefs.getString("study_history", "") ?: ""
        val newHistory = if (history.isBlank()) {
            "$today: $activity"
        } else {
            "$history|$today: $activity"
        }
        prefs.edit().putString("study_history", newHistory).apply()

        val todaySeconds = prefs.getInt("today_study_seconds", 0)
        prefs.edit().putInt("today_study_seconds", todaySeconds + 1).apply()

        val totalSeconds = prefs.getInt("total_study_seconds", 0)
        prefs.edit().putInt("total_study_seconds", totalSeconds + 1).apply()
    }

    fun updateUnitProgress(type: String, unit: Int, progress: Int) {
        prefs.edit().putInt("${type}_unit_${unit}_progress", progress).apply()
        if (progress >= 100) {
            prefs.edit().putBoolean("${type}_unit_${unit}_completed", true).apply()
        }
        updateProgressDisplay()
    }

    fun unlockAchievement(achievementId: String) {
        prefs.edit().putBoolean("achievement_$achievementId", true).apply()
        setupAchievements()
    }
}
