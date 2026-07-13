package com.example.mangaocr.data.local

import android.graphics.BitmapFactory
import java.io.File

class FormatDetector {

    companion object {
        private const val WEBTOON_RATIO_THRESHOLD = 1.5f
        private const val SAMPLE_SIZE = 5
    }

    fun detectFormat(imagePaths: List<String>): String {
        if (imagePaths.isEmpty()) return "unknown"

        val samplePaths = imagePaths.take(SAMPLE_SIZE)
        val aspectRatios = mutableListOf<Float>()

        for (imagePath in samplePaths) {
            try {
                val options = BitmapFactory.Options()
                options.inJustDecodeBounds = true
                BitmapFactory.decodeFile(imagePath, options)
                
                val ratio = options.outHeight.toFloat() / options.outWidth.toFloat()
                aspectRatios.add(ratio)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        if (aspectRatios.isEmpty()) return "unknown"

        val avgRatio = aspectRatios.average().toFloat()
        return if (avgRatio > WEBTOON_RATIO_THRESHOLD) "webtoon" else "manga"
    }

    fun getOrientation(imagePath: String): String {
        return try {
            val options = BitmapFactory.Options()
            options.inJustDecodeBounds = true
            BitmapFactory.decodeFile(imagePath, options)

            val ratio = options.outHeight.toFloat() / options.outWidth.toFloat()
            when {
                ratio > 1.2f -> "portrait"
                ratio < 0.8f -> "landscape"
                else -> "square"
            }
        } catch (e: Exception) {
            "unknown"
        }
    }
}
