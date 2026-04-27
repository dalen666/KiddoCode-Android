# KiddoCode 快速入门指南 🚀

## 项目简介

KiddoCode 是一个专为二年级小朋友设计的安卓编程学习应用，具有以下特点：

- ✨ 明亮活泼的配色，适合儿童
- 🧩 拖拽式积木编程，简单易懂
- 📚 循序渐进的课程体系
- 🏆 成就系统，激发学习兴趣
- 💰 完全免费，无内购

## 如何构建和运行

### 前置条件

1. **Android Studio** (推荐 2022.2.1 或更高版本)
2. **JDK 17** (Android Studio 自带)
3. **Android SDK** (API Level 26 - 34)
4. **安卓设备** 或 **模拟器** (Android 8.0+)

### 构建步骤

1. **打开项目**
   - 启动 Android Studio
   - 选择 "Open an Existing Project"
   - 选择 `KiddoCode` 文件夹

2. **等待 Gradle 同步**
   - Android Studio 会自动下载依赖
   - 等待右下角的进度条完成

3. **创建虚拟设备** (如果没有真机)
   - 点击工具栏的 "Device Manager"
   - 点击 "Create Device"
   - 选择手机型号（推荐 Pixel 4）
   - 选择系统镜像（推荐 Android 11 或更高）
   - 完成创建

4. **运行应用**
   - 在工具栏选择目标设备
   - 点击绿色的 "Run" 按钮 ▶️
   - 等待应用安装和启动

## 应用功能介绍

### 1. 首页 🏠
- 三个功能卡片：开始编程、学习课程、我的进度
- 底部导航栏，方便切换

### 2. 图形化编程 🧩
- 积木库：移动、旋转、重复、等待、说话
- 工作区：放置和排列积木
- 舞台区：查看角色动画
- 运行和清空按钮

### 3. 学习课程 📚
- 5节精心设计的课程
- 进度条显示学习进度
- 星标标记完成状态

### 4. 我的进度 🏆
- 星星数量、完成课程数
- 成就列表
- 继续学习按钮

## 项目文件结构

```
KiddoCode/
├── app/                          # 主应用模块
│   ├── src/
│   │   └── main/
│   │       ├── java/com/kiddocode/app/   # Kotlin源代码
│   │       │   ├── MainActivity.kt       # 主界面
│   │       │   ├── BlockCodingActivity.kt # 编程界面
│   │       │   ├── LessonsActivity.kt    # 课程界面
│   │       │   └── ProgressActivity.kt    # 进度界面
│   │       ├── res/                      # 资源文件
│   │       │   ├── layout/               # 布局文件
│   │       │   ├── values/               # 字符串、颜色、样式
│   │       │   ├── drawable/             # 图标和形状
│   │       │   ├── menu/                 # 菜单
│   │       │   └── xml/                  # 配置文件
│   │       └── AndroidManifest.xml       # 应用清单
│   └── build.gradle.kts                 # 模块构建配置
├── gradle/
├── build.gradle.kts                    # 项目构建配置
├── settings.gradle.kts                 # 项目设置
├── gradle.properties                   # Gradle属性
└── README.md                           # 项目说明
```

## 开发建议

### 为儿童设计的原则
- 使用明亮、高对比度的颜色
- 按钮要大，方便点击
- 文字要大、清晰、简单
- 使用图标和动画，减少文字依赖
- 添加积极的反馈（如Toast提示）

### 后续可以扩展的功能
1. 更多积木类型
2. 保存和加载程序
3. 分享作品功能
4. 更多课程内容
5. 家长/教师后台
6. 云端同步功能

## 上架应用商店准备

如果要上架到 Google Play 或国内应用商店，还需要：

1. 创建正式的应用图标
2. 生成签名密钥
3. 准备应用截图和宣传素材
4. 撰写应用描述
5. 完善隐私政策

## 常见问题

**Q: Gradle 同步失败怎么办？**
A: 检查网络连接，或尝试使用国内镜像源。

**Q: 应用运行后崩溃？**
A: 查看 Logcat 中的错误日志，检查代码和资源文件。

**Q: 如何修改应用名称？**
A: 修改 `res/values/strings.xml` 中的 `app_name`。

---

祝你使用 KiddoCode 编程愉快！🎉
