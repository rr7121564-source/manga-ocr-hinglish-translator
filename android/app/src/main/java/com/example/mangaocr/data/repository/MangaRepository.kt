package com.example.mangaocr.data.repository

import android.app.Application
import android.net.Uri
import com.example.mangaocr.data.local.ZipImporter
import com.example.mangaocr.data.local.FormatDetector
import com.example.mangaocr.data.translator.HinglishTranslator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MangaRepository(private val app: Application) {

    private val zipImporter = ZipImporter(app)
    private val formatDetector = FormatDetector()
    private val translator = HinglishTranslator()

    suspend fun importFromZip(uri: Uri, onProgress: (Int) -> Unit) = withContext(Dispatchers.IO) {
        onProgress(10)
        val extractedPath = zipImporter.extractZip(uri)
        onProgress(25)
        
        val imageFiles = zipImporter.getImageFiles(extractedPath)
        onProgress(40)
        
        val format = formatDetector.detectFormat(imageFiles)
        onProgress(60)
        
        onProgress(80)
        
        val results = imageFiles.mapIndexed { index, file ->
            mapOf(
                "path" to file,
                "format" to format,
                "translated" to translator.toHinglish(file)
            )
        }
        onProgress(100)
        
        return@withContext results
    }
}
