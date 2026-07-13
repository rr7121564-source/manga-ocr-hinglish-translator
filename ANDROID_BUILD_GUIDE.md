# 🚀 Android APK Build Guide

## Quick Start (5 Minutes)

### Step 1: Download Android Studio
- Go to: https://developer.android.com/studio
- Download and install

### Step 2: Clone Repository
```bash
git clone https://github.com/rr7121564-source/manga-ocr-hinglish-translator.git
cd manga-ocr-hinglish-translator
```

### Step 3: Open Android Project
1. Launch Android Studio
2. Click **File → Open**
3. Select the **android** folder
4. Wait for Gradle sync (2-5 minutes)

### Step 4: Build APK
```bash
# Option A: Using Android Studio UI
Click: Build → Build Bundle(s) / APK(s) → Build APK(s)

# Option B: Using Terminal
cd android
./gradlew assembleDebug
```

### Step 5: Find Your APK
**Location:**
```
android/app/build/outputs/apk/debug/app-debug.apk
```

### Step 6: Install on Phone
```bash
adb install android/app/build/outputs/apk/debug/app-debug.apk
```

---

## System Requirements

✅ **Java**: JDK 11+  
✅ **Android SDK**: API 34  
✅ **Gradle**: 8.0+  
✅ **RAM**: 4GB minimum  
✅ **Disk**: 1GB free space  

---

## Build Commands

### Debug APK (for testing)
```bash
./gradlew assembleDebug
# Output: app/build/outputs/apk/debug/app-debug.apk (~60MB)
```

### Release APK (for Play Store)
```bash
./gradlew assembleRelease
# Output: app/build/outputs/apk/release/app-release.apk (~40MB)
```

### App Bundle (for Play Store upload)
```bash
./gradlew bundleRelease
# Output: app/build/outputs/bundle/release/app-release.aab
```

### Clean Build
```bash
./gradlew clean
./gradlew assembleDebug
```

---

## Troubleshooting

### Gradle Sync Error
```bash
# Try:
./gradlew --stop
./gradlew clean
./gradlew assembleDebug
```

### Java Version Error
```bash
# Set JAVA_HOME to JDK 11+
export JAVA_HOME=/path/to/jdk11
```

### Out of Memory
```bash
export GRADLE_OPTS="-Xmx2048m"
./gradlew assembleDebug
```

### Build Slow
- Add to gradle.properties: `org.gradle.parallel=true`
- Increase memory: `org.gradle.jvmargs=-Xmx2048m`

---

## Installation on Device

### Prerequisites
- Enable USB Debugging: Settings → Developer Options → USB Debugging
- Connect phone via USB

### Install Command
```bash
adb install app/build/outputs/apk/debug/app-debug.apk

# Force reinstall:
adb install -r app/build/outputs/apk/debug/app-debug.apk

# Uninstall:
adb uninstall com.example.mangaocr
```

### Launch App
```bash
adb shell am start -n com.example.mangaocr/.MainActivity
```

---

## APK Specifications

| Property | Value |
|----------|-------|
| Min SDK | 24 (Android 7.0) |
| Target SDK | 34 (Android 14) |
| Debug APK Size | ~60 MB |
| Release APK Size | ~40 MB |
| Target Device | Phone/Tablet |
| Orientation | Portrait |

---

## Expected Output

```
BUILD SUCCESSFUL in 45s

Created APK at: android/app/build/outputs/apk/debug/app-debug.apk
```

---

## Next Steps

1. ✅ Build the APK
2. ✅ Test on device
3. ✅ Report bugs
4. ✅ Request features

**Enjoy using Manga OCR! 📱📖**
