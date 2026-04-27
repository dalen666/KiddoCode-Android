package com.kiddocode.app

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import android.content.SharedPreferences
import android.preference.PreferenceManager
import java.util.Calendar

class ParentControlActivity : AppCompatActivity() {

    private lateinit var prefs: SharedPreferences
    private var isAuthenticated = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parent_control)

        prefs = PreferenceManager.getDefaultSharedPreferences(this)

        setupUI()
        checkPasswordProtection()
    }

    private fun setupUI() {
        val passwordCard = findViewById<CardView>(R.id.password_card)
        val controlCard = findViewById<CardView>(R.id.control_card)
        val reportCard = findViewById<CardView>(R.id.report_card)

        // 密码设置
        setupPasswordSection()

        // 学习时间限制
        setupTimeLimitSection()

        // 内容访问权限
        setupContentPermissionSection()

        // 学习报告查看
        setupReportSection()

        // 其他安全设置
        setupSecuritySection()
    }

    private fun checkPasswordProtection() {
        val hasPassword = prefs.getBoolean("has_parent_password", false)
        if (hasPassword) {
            showPasswordDialog()
        } else {
            isAuthenticated = true
            showControlPanel()
        }
    }

    private fun showPasswordDialog() {
        // 显示密码输入对话框
        val passwordInput = EditText(this)
        passwordInput.hint = "输入家长密码"

        androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle("家长验证")
            .setView(passwordInput)
            .setPositiveButton("验证") { _, _ ->
                val savedPassword = prefs.getString("parent_password", "")
                if (passwordInput.text.toString() == savedPassword) {
                    isAuthenticated = true
                    showControlPanel()
                } else {
                    Toast.makeText(this, "密码错误", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
            .setNegativeButton("取消") { _, _ ->
                finish()
            }
            .setCancelable(false)
            .show()
    }

    private fun setupPasswordSection() {
        val setPasswordBtn = findViewById<Button>(R.id.set_password_btn)
        val passwordStatus = findViewById<TextView>(R.id.password_status)

        val hasPassword = prefs.getBoolean("has_parent_password", false)
        passwordStatus.text = if (hasPassword) "✅ 已设置" else "❌ 未设置"

        setPasswordBtn.setOnClickListener {
            showSetPasswordDialog()
        }
    }

    private fun showSetPasswordDialog() {
        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(48, 24, 48, 24)

        val passwordInput = EditText(this)
        passwordInput.hint = "设置新密码"
        layout.addView(passwordInput)

        val confirmInput = EditText(this)
        confirmInput.hint = "确认密码"
        layout.addView(confirmInput)

        androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle("设置家长密码")
            .setView(layout)
            .setPositiveButton("保存") { _, _ ->
                val pass1 = passwordInput.text.toString()
                val pass2 = confirmInput.text.toString()

                if (pass1.length < 4) {
                    Toast.makeText(this, "密码至少4位", Toast.LENGTH_SHORT).show()
                } else if (pass1 != pass2) {
                    Toast.makeText(this, "两次密码不一致", Toast.LENGTH_SHORT).show()
                } else {
                    prefs.edit()
                        .putString("parent_password", pass1)
                        .putBoolean("has_parent_password", true)
                        .apply()
                    Toast.makeText(this, "密码设置成功", Toast.LENGTH_SHORT).show()
                    checkPasswordProtection()
                }
            }
            .setNegativeButton("取消", null)
            .show()
    }

    private fun setupTimeLimitSection() {
        val timeLimitSwitch = findViewById<Switch>(R.id.time_limit_switch)
        val timeSeekBar = findViewById<SeekBar>(R.id.time_seekbar)
        val timeText = findViewById<TextView>(R.id.time_limit_text)

        val isEnabled = prefs.getBoolean("time_limit_enabled", false)
        timeLimitSwitch.isChecked = isEnabled

        val savedLimit = prefs.getInt("daily_time_limit", 60)
        timeSeekBar.progress = savedLimit
        timeText.text = "每日学习时间: ${savedLimit} 分钟"

        timeSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                timeText.text = "每日学习时间: ${progress} 分钟"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                prefs.edit().putInt("daily_time_limit", seekBar?.progress ?: 60).apply()
            }
        })

        timeLimitSwitch.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("time_limit_enabled", isChecked).apply()
        }
    }

    private fun setupContentPermissionSection() {
        val scratchSwitch = findViewById<Switch>(R.id.allow_scratch_switch)
        val pythonSwitch = findViewById<Switch>(R.id.allow_python_switch)
        val internetSwitch = findViewById<Switch>(R.id.allow_internet_switch)

        scratchSwitch.isChecked = prefs.getBoolean("allow_scratch", true)
        pythonSwitch.isChecked = prefs.getBoolean("allow_python", true)
        internetSwitch.isChecked = prefs.getBoolean("allow_internet", false)

        scratchSwitch.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("allow_scratch", isChecked).apply()
        }

        pythonSwitch.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("allow_python", isChecked).apply()
        }

        internetSwitch.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("allow_internet", isChecked).apply()
        }
    }

    private fun setupReportSection() {
        val todayTimeText = findViewById<TextView>(R.id.today_study_time)
        val weekTimeText = findViewById<TextView>(R.id.week_study_time)
        val completedProjectsText = findViewById<TextView>(R.id.completed_projects)
        const totalLessonsText = findViewById<TextView>(R.id.total_lessons)

        val todayTime = prefs.getInt("today_study_seconds", 0)
        val weekTime = prefs.getInt("week_study_seconds", 0)
        val completed = prefs.getInt("completed_projects", 0)
        val totalLessons = prefs.getInt("total_lessons_done", 0)

        todayTimeText.text = "今日学习: ${todayTime / 60} 分钟"
        weekTimeText.text = "本周学习: ${weekTime / 3600} 小时"
        completedProjectsText.text = "完成项目: $completed 个"
        totalLessonsText.text = "总课程数: $totalLessons"

        val resetBtn = findViewById<Button>(R.id.reset_stats_btn)
        resetBtn.setOnClickListener {
            androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("确认重置")
                .setMessage("确定要重置学习统计吗？")
                .setPositiveButton("确定") { _, _ ->
                    prefs.edit()
                        .putInt("today_study_seconds", 0)
                        .putInt("week_study_seconds", 0)
                        .apply()
                    Toast.makeText(this, "统计已重置", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("取消", null)
                .show()
        }
    }

    private fun setupSecuritySection() {
        val adSwitch = findViewById<Switch>(R.id.block_ads_switch)
        val internetSwitch = findViewById<Switch>(R.id.restrict_internet_switch)

        adSwitch.isChecked = prefs.getBoolean("block_ads", true)
        internetSwitch.isChecked = prefs.getBoolean("restrict_internet", true)

        adSwitch.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("block_ads", isChecked).apply()
        }

        internetSwitch.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("restrict_internet", isChecked).apply()
        }
    }

    private fun showControlPanel() {
        // 显示完整控制界面
        findViewById<LinearLayout>(R.id.parent_control_panel).visibility = View.VISIBLE
    }
}
