# 🚀 KiddoCode 快速入门指南

## 三种构建方式，任你选择！

---

## 🎯 方式一：GitHub Actions 在线构建（最简单）

**无需安装任何软件！**

### 步骤：

1. **上传项目到 GitHub
   - 创建一个新的 GitHub 仓库
   - 把 `KiddoCode-Unified/legacy/KiddoCode/` 文件夹的内容上传

2. **触发构建
   - 推送到 GitHub 会自动开始构建
   - 或点击仓库的 `Actions` → `Build Android APK` → `Run workflow`

3. **下载 APK**
   - 构建完成后，进入 Actions 页面
   - 点击最新的构建
   - 在页面底部的 `Artifacts` 区域下载
   - 两个文件可选：
     - `kiddo-code-debug` (调试版，可直接安装)
     - `kiddo-code-release-unsigned` (发布版，需要签名)

**预估时间：5-10分钟

---

## 💻 方式二：Android Studio 本地构建（最稳定）

### 1. 安装 Android Studio
- 下载：https://developer.android.com/studio
- 安装并启动，选择 "Standard" 安装

### 2. 打开项目
- 启动 Android Studio
- 点击 "Open"
- 选择文件夹：`KiddoCode-Unified/legacy/KiddoCode`
- 等待 Gradle 同步完成（首次约3-10分钟）

### 3. 构建 APK
- 菜单：`Build` → `Build Bundle(s) / APK(s)` → `Build APK(s)`
- 等待构建完成（2-5分钟）
- 点击通知中的 "locate" 找到 APK

**APK位置：** `app/build/outputs/apk/debug/app-debug.apk

---

## ⚡ 方式三：命令行快速构建（高级用户）

### Windows 用户
1. 确保已安装 JDK 17+
2. 双击运行 `quick-build.bat`
3. 等待构建完成

### macOS/Linux 用户
```bash
cd KiddoCode-Unified/legacy/KiddoCode
chmod +x gradlew
./gradlew assembleDebug
```

---

## 📱 安装 APK 到手机

### 方法 A：直接安装
1. 将 `app-debug.apk` 复制到手机
2. 在手机文件管理器中找到并点击
3. 允许安装未知来源应用
4. 完成安装！

### 方法 B：使用 ADB（需要 USB 调试）
```bash
adb install app-debug.apk
```

### 启用 USB 调试
1. 手机设置 → 关于手机
2. 连续点击版本号 7 次
3. 返回设置 → 开发者选项
4. 开启 USB 调试
5. 用 USB 线连接手机和电脑
6. 在手机上授权调试

---

## 📦 项目文件说明

```
KiddoCode/
├── .github/workflows/
│   └── build.yml          # GitHub Actions 配置
├── app/
│   └── src/main/
│       ├── java/          # Kotlin 源代码
│       ├── res/           # UI 资源
│       ├── assets/          # 游戏文件
│       └── AndroidManifest.xml
├── gradlew                 # Gradle 脚本 (macOS/Linux)
├── gradlew.bat             # Gradle 脚本 (Windows)
├── quick-build.bat         # 快速构建脚本 (Windows)
├── BUILD_INSTRUCTIONS.md   # 详细构建指南
├── QUICK_START.md         # 本文档
└── PROJECT_SUMMARY.md      # 项目总结
```

---

## ✨ 应用功能预览

KiddoCode 包含：
- 🎨 美观的 Material Design 界面
- 🧩 图形化编程学习
- 📚 分阶段课程系统
- 🎮 6 款教育游戏
- 📊 学习进度追踪
- 📱 Android 8.0 - 16 全兼容

---

## ❓ 需要帮助？

查看详细文档：`BUILD_INSTRUCTIONS.md`

---

## 🎉 开始构建吧！

推荐优先尝试 **GitHub Actions** - 最简单，无需安装任何软件！
