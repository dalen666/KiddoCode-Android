# KiddoCode Android 16 应用构建指南

## 📱 应用概述
KiddoCode是一个专为儿童设计的编程教育应用，完全兼容Android 16 (API级别35)，包含以下完整功能：

### 核心功能
- ✅ **主页界面** - 美观的Material Design设计，包含所有功能入口
- ✅ **图形化编程** - 积木拖拽式编程学习
- ✅ **学习课程** - 分阶段的编程课程系统
- ✅ **游戏中心** - 6款寓教于乐的编程游戏
  - 数学冒险王
  - 迷宫探险
  - 记忆翻牌
  - 接物小游戏
  - 推箱子
  - 跑酷大冒险
- ✅ **学习进度** - 学习成果追踪和展示

## 🚀 快速构建方案（三种方式）

---

## 方案一：使用在线构建服务（最简单，无需安装）

### 使用 GitHub Actions（推荐）

1. **创建GitHub仓库**
   - 将项目文件夹上传到GitHub
   - 确保包含所有源代码文件

2. **添加构建工作流**
   - 在仓库中创建 `.github/workflows/build.yml` 文件
   - 使用以下内容：
   ```yaml
   name: Build Android APK
   on: [push, workflow_dispatch]
   jobs:
     build:
       runs-on: ubuntu-latest
       steps:
         - uses: actions/checkout@v4
         - name: Set up JDK 17
           uses: actions/setup-java@v4
           with:
             java-version: '17'
             distribution: 'temurin'
         - name: Grant execute permission for gradlew
           run: chmod +x gradlew
         - name: Build Debug APK
           run: ./gradlew assembleDebug
         - name: Upload APK
           uses: actions/upload-artifact@v4
           with:
             name: app-debug
             path: app/build/outputs/apk/debug/app-debug.apk
   ```

3. **触发构建**
   - 推送到GitHub会自动触发构建
   - 或在Actions页面手动触发
   - 构建完成后在Artifacts中下载APK

### 使用其他在线服务
- **AppVeyor**: https://www.appveyor.com
- **CircleCI**: https://circleci.com
- **Bitrise**: https://www.bitrise.io

---

## 方案二：使用Android Studio（推荐，最稳定）

### 1. 下载和安装Android Studio
- 访问：https://developer.android.com/studio
- 下载适合你系统的版本
- 运行安装程序，按照向导完成安装

### 2. 快速配置（自动完成）
1. 首次启动Android Studio
2. 选择 "Standard" 安装类型
3. 等待自动下载所有必要组件（包括SDK）
4. 完成后点击 "Finish"

### 3. 打开项目
1. 在欢迎界面点击 "Open"
2. 导航到：`KiddoCode-Unified/legacy/KiddoCode`
3. 点击 "OK"
4. 等待Gradle同步（首次可能需要3-10分钟）

### 4. 构建APK（仅需3步）
1. 点击菜单：`Build` → `Build Bundle(s) / APK(s)` → `Build APK(s)`
2. 等待构建完成（2-5分钟）
3. 点击通知中的 "locate" 查看APK

### APK位置
```
app/build/outputs/apk/debug/app-debug.apk
```

---

## 方案三：使用命令行（适合高级用户）

### 前置要求
- JDK 17 或更高版本
- Android SDK（API 35）
- 命令行工具

### Windows 构建
```cmd
cd KiddoCode-Unified/legacy/KiddoCode
gradlew.bat assembleDebug
```

### macOS/Linux 构建
```bash
cd KiddoCode-Unified/legacy/KiddoCode
chmod +x gradlew
./gradlew assembleDebug
```

### 构建输出
- Debug APK: `app/build/outputs/apk/debug/app-debug.apk`
- Release APK: `app/build/outputs/apk/release/app-release.apk`

---

## 📲 安装和测试

### 安装到Android设备
1. **启用开发者选项**
   - 进入手机「设置」→「关于手机」
   - 连续点击「版本号」7次
   - 返回设置，找到「开发者选项」

2. **启用USB调试**
   - 在开发者选项中开启「USB调试」
   - 用USB线连接手机和电脑
   - 在手机上授权调试

3. **安装APK**
   - 方式A：在Android Studio中点击 "Run"
   - 方式B：直接复制APK文件到手机，点击安装
   - 方式C：使用命令：`adb install app-debug.apk`

### 使用模拟器测试
1. 在Android Studio中点击 "Device Manager"
2. 点击 "Create Device"
3. 选择设备型号（推荐 Pixel 6）
4. 选择系统镜像 "Android 16 (API 35)"
5. 点击 "Finish" 创建并启动模拟器
6. 点击 "Run" 按钮安装应用

---

## 📁 项目结构

```
KiddoCode/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/kiddocode/app/      # Kotlin源代码
│   │       │   ├── MainActivity.kt          # 主界面
│   │       │   ├── BlockCodingActivity.kt   # 图形化编程
│   │       │   ├── LessonsActivity.kt       # 学习课程
│   │       │   ├── ProgressActivity.kt      # 学习进度
│   │       │   ├── GameCenterActivity.kt    # 游戏中心
│   │       │   └── WebGameActivity.kt       # Web游戏加载器
│   │       ├── res/                         # 资源文件
│   │       │   ├── layout/                  # 界面布局XML
│   │       │   ├── values/                  # 颜色、字符串、主题
│   │       │   ├── drawable/                # 图标和图形资源
│   │       │   └── menu/                    # 底部导航菜单
│   │       ├── assets/                      # Web游戏文件
│   │       └── AndroidManifest.xml          # 应用清单
│   └── build.gradle.kts                     # 应用级构建配置
├── gradle/
│   └── wrapper/                             # Gradle wrapper文件
├── build.gradle.kts                         # 项目级构建配置
├── settings.gradle.kts                      # 项目设置
├── gradle.properties                        # Gradle属性
├── gradlew.bat                              # Gradle执行脚本(Windows)
├── quick-build.bat                          # 快速构建脚本(Windows)
└── BUILD_INSTRUCTIONS.md                    # 本文档
```

---

## 🎨 Material Design 设计规范
应用完全遵循Google Material Design设计规范：
- 使用Material Components库
- 主题化颜色方案
- 响应式布局
- 卡片式设计
- 平滑的动画过渡

---

## 🔍 兼容性检查
应用已针对Android 16进行优化：
- ✅ targetSdk = 35
- ✅ compileSdk = 35
- ✅ minSdk = 26 (支持Android 8.0+)
- ✅ 使用最新的AndroidX库
- ✅ 支持分区存储
- ✅ 兼容深色模式

---

## 📞 常见问题

### Q: 最简单的方式是什么？
A: 使用在线构建服务（如GitHub Actions）最简单，无需安装任何软件。

### Q: Gradle同步失败怎么办？
A: 
1. 检查网络连接
2. 尝试使用国内镜像源
3. 删除项目根目录下的 `.gradle` 文件夹
4. 重新同步项目

### Q: 找不到SDK怎么办？
A:
1. 打开 `File` → `Project Structure` → `SDK Location`
2. 确认Android SDK路径正确
3. 或点击 `Download` 自动下载

### Q: 构建速度慢怎么办？
A:
1. 已在 `gradle.properties` 中配置优化
2. 使用离线模式（需要先下载所有依赖）

---

## 🎉 开始构建！

选择上面任意一种方式开始构建，推荐优先尝试：
1. **最简单** → GitHub Actions在线构建
2. **最稳定** → Android Studio本地构建

构建成功后，你将获得一个完整的KiddoCode Android应用APK！
