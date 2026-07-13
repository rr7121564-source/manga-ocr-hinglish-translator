# Manga OCR Hinglish Translator - Native Android App

## Features

✅ **ZIP File Import** - Import manga/webtoon from ZIP files  
✅ **Format Auto-Detection** - Detect webtoon (vertical) vs manga (horizontal)  
✅ **ML Kit OCR** - Google ML Kit for text recognition  
✅ **Hinglish Translation** - Convert Hindi to Hinglish (Roman script)  
✅ **Offline Processing** - Works without internet  
✅ **Progress Tracking** - Real-time import and processing progress  

## Technology Stack

- **Language**: Kotlin
- **UI**: Android Fragment Architecture, Navigation Component
- **OCR**: Google ML Kit Text Recognition
- **Image Processing**: Android Graphics
- **File Handling**: Apache Commons Compress
- **Async**: Kotlin Coroutines
- **Architecture**: MVVM with Repository Pattern

## Project Structure

```
android/
├── app/
│   ├── src/main/
│   │   ├── java/com/example/mangaocr/
│   │   │   ├── MainActivity.kt
│   │   │   ├── ui/
│   │   │   │   └── home/
│   │   │   │       ├── HomeFragment.kt
│   │   │   │       └── HomeViewModel.kt
│   │   │   └── data/
│   │   │       ├── repository/
│   │   │       │   └── MangaRepository.kt
│   │   │       ├── local/
│   │   │       │   ├── ZipImporter.kt
│   │   │       │   └── FormatDetector.kt
│   │   │       ├── ocr/
│   │   │       │   └── TextExtractor.kt
│   │   │       └── translator/
│   │   │           └── HinglishTranslator.kt
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   ├── values/
│   │   │   └── navigation/
│   │   └── AndroidManifest.xml
│   └── build.gradle
├── build.gradle
└── settings.gradle
```

## Installation & Setup

### Prerequisites

- Android Studio Flamingo or higher
- Android SDK 24 (API level 24+)
- Java 11 or higher
- Kotlin 1.8.0 or higher

### Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/rr7121564-source/manga-ocr-hinglish-translator.git
   cd manga-ocr-hinglish-translator/android
   ```

2. **Open in Android Studio**
   - File → Open → Select the `android` folder

3. **Sync Gradle**
   - Android Studio will automatically download dependencies

4. **Build APK**
   - Build → Build Bundle(s) / APK(s) → Build APK(s)

5. **Run on Device/Emulator**
   - Connect device or launch emulator
   - Click Run (Shift + F10)

## Permissions Required

```xml
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.CAMERA" />
```

## How to Use

1. **Launch the app**
   - Open "Manga OCR Hinglish" app

2. **Import ZIP File**
   - Tap "Import ZIP File" button
   - Select a ZIP containing manga/webtoon images

3. **Processing**
   - App automatically:
     - Extracts images
     - Detects format (manga/webtoon)
     - Extracts text with OCR
     - Translates to Hinglish

4. **View Results**
   - See extracted text in Hinglish
   - View images with translations

## Build & Release

### Debug APK
```bash
./gradlew assembleDebug
```

### Release APK
```bash
./gradlew assembleRelease
```

### Create AAB for Play Store
```bash
./gradlew bundleRelease
```

## Dependencies

- **androidx.appcompat**: Android compatibility library
- **androidx.lifecycle**: ViewModels and lifecycle management
- **kotlinx.coroutines**: Async programming
- **com.google.mlkit:text-recognition**: ML Kit OCR
- **org.apache.commons:commons-compress**: ZIP file handling
- **com.google.code.gson**: JSON processing

## Next Steps (Future Enhancements)

- [ ] Add image viewer with page navigation
- [ ] Implement cloud OCR API integration
- [ ] Add bookmark/favorite chapters
- [ ] Create reading history
- [ ] Add offline file storage
- [ ] Implement custom translation preferences
- [ ] Add text highlighting and annotation
- [ ] Support for multiple languages

## Troubleshooting

### APK Installation Fails
- Enable "Install from Unknown Sources" in device settings
- Ensure device has Android 5.0+ (API 21+)

### OCR Not Working
- Grant camera and storage permissions
- Ensure ML Kit models are downloaded (app will auto-download)
- Check device has enough storage space

### ZIP Import Error
- Verify ZIP file is valid
- Ensure images are in supported formats (.jpg, .png, .webp, .gif)
- Check device has read storage permissions

## Support

For issues or feature requests:
- Open GitHub issue: https://github.com/rr7121564-source/manga-ocr-hinglish-translator/issues
- Contact: rr7121564@gmail.com

## License

MIT License - See LICENSE file for details
