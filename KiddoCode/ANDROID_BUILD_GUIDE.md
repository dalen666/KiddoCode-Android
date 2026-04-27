# KiddoCode Android 项目构建指南

## 📱 项目概述

这是 KiddoCode 儿童编程教育应用的 Android 原生项目版本，支持 Android 16 (API 36)。

### 主要功能
- ✅ 图形化编程界面（积木拖拽）
- ✅ 学习课程系统
- ✅ 学习进度追踪
- ✅ 游戏中心（集成 Web 游戏）
- ✅ 支持 Android 8.0 - Android 16

## 🚀 快速开始

### 环境要求
- **Android Studio**: Hedgehog (2023.1.1) 或更高版本
- **JDK**: 17 或更高版本（Android Studio 自带）
- **Android SDK**: API 36 (Android 16)
- **Gradle**: 8.7

### 构建步骤

#### 1. 打开项目
1. 启动 Android Studio
2. 选择 `Open an Existing Project`
3. 选择此目录：`KiddoCode-Unified/legacy/KiddoCode`
4. 等待 Gradle 同步完成（首次可能需要下载依赖）

#### 2. 安装 SDK
如果缺少 Android 16 SDK：
1. 打开 `Tools` → `SDK Manager`
2. 勾选 `Android 16.0 (API 36)`
3. 点击 `Apply` 安装

#### 3. 运行应用
**使用模拟器：**
1. 点击工具栏的 `Device Manager`
2. 创建新设备（推荐 Pixel 6 或更高）
3. 选择系统镜像（Android 16）
4. 启动模拟器
5. 点击 `Run` 按钮（绿色三角形）

**使用真机：**
1. 在手机上开启「开发者选项」和「USB 调试」
2. 用 USB 连接手机
3. 在 Android Studio 中选择你的设备
4. 点击 `Run`

### 生成 APK

#### Debug APK（测试用）
1. `Build` → `Build Bundle(s) / APK(s)` → `Build APK(s)`
2. 构建完成后点击通知中的 `locate`
3. APK 位置：`app/build/outputs/apk/debug/app-debug.apk`

#### Release APK（正式发布）
1. 需要先配置签名密钥
2. `Build` → `Generate Signed Bundle / APK`
3. 选择 `APK` 并按照向导操作

## 📂 项目结构

```
KiddoCode/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/kiddocode/app/      # Kotlin 源代码
│   │       │   ├── MainActivity.kt          # 主界面
│   │       │   ├── BlockCodingActivity.kt   # 图形化编程
│   │       │   ├── LessonsActivity.kt       # 课程
│   │       │   ├── ProgressActivity.kt      # 进度
│   │       │   ├── GameCenterActivity.kt    # 游戏中心
│   │       │   └── WebGameActivity.kt       # Web 游戏
│   │       ├── res/                         # 资源文件
│   │       │   ├── layout/                  # 界面布局
│   │       │   ├── values/                  # 颜色、字符串、主题
│   │       │   ├── drawable/                # 图标和形状
│   │       │   └── menu/                    # 菜单
│   │       ├── assets/                      # Web 游戏资源
│   │       └── AndroidManifest.xml          # 应用清单
│   └── build.gradle.kts                     # 应用级构建配置
├── gradle/
├── build.gradle.kts                         # 项目级构建配置
├── settings.gradle.kts                      # 项目设置
└── gradle.properties                        # Gradle 属性
```

## 🎮 集成 Web 游戏

项目支持通过 WebView 加载现有的 HTML5 游戏。

### 添加新游戏步骤

1. 将游戏的 HTML/CSS/JS 文件复制到 `app/src/main/assets/` 目录
2. 在 `GameCenterActivity.kt` 中添加新游戏的入口
3. 在 `activity_game_center.xml` 中添加新游戏卡片

### 示例
```kotlin
cardMyGame.setOnClickListener {
    openGame("我的游戏", "my-game.html")
}
```

## 🛠️ 常见问题

### Gradle 同步失败
- 检查网络连接
- 尝试使用国内镜像源
- 删除 `.gradle` 文件夹重新同步

### 应用图标显示默认图标
当前使用的是简单的占位图标。如需自定义：
1. 在 Android Studio 中右键 `res` 文件夹
2. `New` → `Image Asset`
3. 按照向导创建应用图标

### Web 游戏加载问题
- 确保文件在 `assets` 目录中
- 检查文件名和路径大小写
- 添加 `usesCleartextTraffic="true"` 已在 AndroidManifest 中配置

## 📱 支持的 Android 版本

- **最低版本**: Android 8.0 (API 26)
- **目标版本**: Android 16 (API 36)
- **编译版本**: Android 16 (API 36)

## 📄 许可证

MIT License - 详见项目根目录

## 🎉 下一步

- 将现有的 KiddoCode Web 游戏文件复制到 `assets` 目录
- 在 Android Studio 中测试运行
- 生成签名 APK 用于发布

祝你使用愉快！
