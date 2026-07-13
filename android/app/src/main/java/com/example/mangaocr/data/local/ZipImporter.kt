package com.example.mangaocr.data.local

import android.app.Application
import android.net.Uri
import java.io.File
import java.util.zip.ZipFile

class ZipImporter(private val app: Application) {

    fun extractZip(uri: Uri): String {
        val outputDir = File(app.cacheDir, "manga_extracted")
        outputDir.mkdirs()

        val tempZip = File.createTempFile("manga", ".zip", app.cacheDir)
        app.contentResolver.openInputStream(uri)?.use { input ->
            tempZip.outputStream().use { output ->
                input.copyTo(output)
            }
        }

        val zipFile = ZipFile(tempZip)
        zipFile.entries().asSequence().forEach { entry ->
            val outputFile = File(outputDir, entry.name)
            outputFile.parentFile?.mkdirs()
            
            if (!entry.isDirectory) {
                zipFile.getInputStream(entry).use { input ->
                    outputFile.outputStream().use { output ->
                        input.copyTo(output)
                    }
                }
            }
        }
        zipFile.close()
        
        return outputDir.absolutePath
    }

    fun getImageFiles(dirPath: String): List<String> {
        val imageExtensions = setOf(".jpg", ".jpeg", ".png", ".gif", ".webp")
        val dir = File(dirPath)
        
        return dir.walkTopDown()
            .filter { it.isFile }
            .filter { file ->
                imageExtensions.any { ext ->
                    file.name.lowercase().endsWith(ext)
                }
            }
            .map { it.absolutePath }
            .sorted()
            .toList()
    }
}
