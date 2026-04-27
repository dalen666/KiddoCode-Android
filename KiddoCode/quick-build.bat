@echo off
chcp 65001 >nul
echo ========================================
echo   KiddoCode 快速构建脚本
echo ========================================
echo.

cd /d "%~dp0"

echo [1/3] 检查环境...
echo.

where java >nul 2>&1
if %errorlevel% neq 0 (
    echo [错误] 未找到 Java！
    echo.
    echo 请先安装 JDK 17 或更高版本
    echo 下载地址: https://adoptium.net/
    echo.
    pause
    exit /b 1
)

echo [OK] Java 已安装
java -version
echo.

if not exist "gradlew.bat" (
    echo [错误] 未找到 gradlew.bat！
    echo.
    pause
    exit /b 1
)

echo [OK] Gradle Wrapper 就绪
echo.
echo [2/3] 开始构建 Debug APK...
echo.
echo 这可能需要几分钟时间，请耐心等待...
echo.

call gradlew.bat assembleDebug

if %errorlevel% neq 0 (
    echo.
    echo [错误] 构建失败！
    echo.
    pause
    echo.
    echo 可能的原因：
    echo 1. 未安装 Android SDK
    echo 2. 网络连接问题
    echo 3. 依赖下载失败
    echo.
    echo 建议使用 Android Studio 构建会更稳定
    echo.
    pause
    exit /b 1
)

echo.
echo ========================================
echo   [成功] 构建完成！
echo ========================================
echo.

set APK_PATH=app\build\outputs\apk\debug\app-debug.apk

if exist "%APK_PATH%" (
    echo APK 文件位置:
    echo %CD%\%APK_PATH%
    echo.
    echo 正在打开输出目录...
    explorer /select,"%CD%\%APK_PATH%"
) else (
    echo APK 文件应该在:
    echo app\build\outputs\apk\debug\
)

echo.
echo 安装 APK 到手机:
echo 1. 启用 USB 调试
echo 2. 连接手机
echo 3. 运行: adb install %APK_PATH%
echo.
pause
