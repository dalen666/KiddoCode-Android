# 🎮 KiddoCode - Android 16 儿童编程教育应用

[![Build Android APK](https://img.shields.io/badge/Build-Android-green?logo=android)](https://github.com/your-repo/actions)
[![API 35](https://img.shields.io/badge/API-35-brightgreen?logo=android)](https://developer.android.com/about/versions/16)
[![Material Design](https://img.shields.io/badge/Material%20Design-3-blue?logo=materialdesign)](https://m3.material.io)
[![License MIT](https://img.shields.io/badge/License-MIT-yellow)](LICENSE)

## 📱 应用简介

KiddoCode 是一个专为儿童设计的编程教育应用，完全兼容 Android 16 (API 级别 35)。通过游戏化的学习方式，让孩子们在玩乐中掌握编程思维！

### 🌟 核心功能

- **🏠 精美主页** - Material Design 3 风格，直观易用
- **🧩 图形化编程** - 积木拖拽式编程，零基础友好
- **📚 分阶课程** - 从简单到复杂，循序渐进的学习路径
- **🎮 游戏中心** - 6 款教育游戏，寓教于乐
  - 数学冒险王
  - 迷宫探险
  - 记忆翻牌
  - 接物小游戏
  - 推箱子
  - 跑酷大冒险
- **📊 进度追踪** - 记录学习成果，激发学习动力

## 🚀 快速开始

### 🎯 方式一：GitHub Actions 在线构建（推荐，最简单）

无需安装任何软件！

1. 将此项目上传到 GitHub
2. 打开 `Actions` 标签页
3. 运行 `Build Android APK` 工作流
4. 下载构建好的 APK 文件

详细步骤：[QUICK_START.md](QUICK_START.md)

### 💻 方式二：Android Studio 本地构建

1. 下载并安装 [Android Studio](https://developer.android.com/studio)
2. 打开此项目文件夹
3. 点击 `Build` → `Build Bundle(s) / APK(s)` → `Build APK(s)`

详细步骤：[BUILD_INSTRUCTIONS.md](BUILD_INSTRUCTIONS.md)

### ⚡ 方式三：命令行构建

**Windows:**
```cmd
quick-build.bat
```

**macOS/Linux:**
```bash
chmod +x gradlew
./gradlew assembleDebug
```

## 📦 构建产物

构建成功后，APK 文件位于：

- **Debug APK:** `app/build/outputs/apk/debug/app-debug.apk`
- **Release APK:** `app/build/outputs/apk/release/app-release-unsigned.apk`

## 📋 技术规格

| 项目 | 详情 |
|------|------|
| **目标 SDK** | 35 (Android 16) |
| **最低 SDK** | 26 (Android 8.0) |
| **编程语言** | Kotlin |
| **UI 框架** | Material Design Components |
| **构建工具** | Gradle 8.7 |
| **JDK 版本** | 17+ |

## 🎨 设计特点

- ✅ Material Design 3 规范
- ✅ 响应式布局
- ✅ 卡片式设计
- ✅ 流畅的动画过渡
- ✅ 儿童友好的配色方案
- ✅ 支持深色模式

## 📂 项目结构

```
KiddoCode/
├── .github/
│   └── workflows/
│       └── build.yml              # GitHub Actions 构建配置
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/kiddocode/app/  # Kotlin 源代码
│   │       │   ├── MainActivity.kt
│   │       │   ├── BlockCodingActivity.kt
│   │       │   ├── LessonsActivity.kt
│   │       │   ├── ProgressActivity.kt
│   │       │   ├── GameCenterActivity.kt
│   │       │   └── WebGameActivity.kt
│   │       ├── res/               # UI 资源文件
│   │       ├── assets/            # HTML5 游戏文件
│   │       └── AndroidManifest.xml
│   └── build.gradle.kts           # 应用级构建配置
├── gradle/
│   └── wrapper/                   # Gradle Wrapper
├── gradlew                        # Gradle 脚本 (Linux/macOS)
├── gradlew.bat                    # Gradle 脚本 (Windows)
├── quick-build.bat                # 快速构建脚本 (Windows)
├── build.gradle.kts               # 项目级构建配置
├── settings.gradle.kts            # 项目设置
├── gradle.properties              # Gradle 配置
├── QUICK_START.md                 # 快速入门指南
├── BUILD_INSTRUCTIONS.md          # 详细构建指南
├── PROJECT_SUMMARY.md             # 项目总结
└── README.md                      # 本文档
```

## 📖 文档导航

| 文档 | 用途 |
|------|------|
| [QUICK_START.md](QUICK_START.md) | 👈 推荐先看这个！快速入门 |
| [BUILD_INSTRUCTIONS.md](BUILD_INSTRUCTIONS.md) | 详细构建指南 |
| [PROJECT_SUMMARY.md](PROJECT_SUMMARY.md) | 项目功能总结 |

## 💡 常见问题

**Q: 哪种构建方式最简单？**
A: GitHub Actions 在线构建，无需安装任何软件。

**Q: 构建需要多长时间？**
A: 在线构建约 5-10 分钟，本地构建约 2-5 分钟。

**Q: 支持哪些 Android 版本？**
A: Android 8.0 (API 26) 到 Android 16 (API 35)。

**Q: 如何安装 APK 到手机？**
A: 直接复制 APK 文件到手机点击安装，或使用 ADB。

更多问题见：[BUILD_INSTRUCTIONS.md](BUILD_INSTRUCTIONS.md)

## 🎉 开始使用

1. **选择构建方式** - 推荐 GitHub Actions 在线构建
2. **下载 APK** - 构建完成后获取
3. **安装应用** - 安装到 Android 设备
4. **开始学习** - 享受 KiddoCode 的编程之旅！

## 📄 许可证

MIT License - 详见 LICENSE 文件

---

**祝孩子们在编程的世界里快乐学习！** 🎮✨
