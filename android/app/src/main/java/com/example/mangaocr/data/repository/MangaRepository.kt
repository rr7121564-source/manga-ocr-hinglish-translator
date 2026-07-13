package com.example.mangaocr.data.repository

import android.app.Application
import android.net.Uri
import com.example.mangaocr.data.local.ZipImporter
import com.example.mangaocr.data.local.FormatDetector
import com.example.mangaocr.data.ocr.TextExtractor
import com.example.mangaocr.data.translator.HinglishTranslator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MangaRepository(private val app: Application) {

    private val zipImporter = ZipImporter(app)
    private val formatDetector = FormatDetector()
    private val textExtractor = TextExtractor(app)
    private val translator = HinglishTranslator()

    suspend fun importFromZip(uri: Uri, onProgress: (Int) -> Unit) = withContext(Dispatchers.IO) {
        onProgress(10)
        
        // Extract ZIP
        val extractedPath = zipImporter.extractZip(uri)
        onProgress(25)
        
        // Get images
        val imageFiles = zipImporter.getImageFiles(extractedPath)
        onProgress(40)
        
        // Detect format
        val format = formatDetector.detectFormat(imageFiles)
        onProgress(60)
        
        // Extract text
        val ocrResults = textExtractor.extractText(imageFiles)
        onProgress(80)
        
        // Translate
        val translated = ocrResults.map { result ->
            result.copy(hinglish = translator.toHinglish(result.text))
        }
        onProgress(100)
        
        return@withContext translated
    }
}
