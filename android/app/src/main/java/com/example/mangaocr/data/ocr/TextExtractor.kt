package com.example.mangaocr.data.ocr

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.devanagari.DevanagariTextRecognizer

data class OCRResult(
    val text: String,
    val imagePath: String,
    val confidence: Float,
    var hinglish: String = ""
)

class TextExtractor(private val app: Application) {

    private val recognizer by lazy {
        TextRecognition.getClient()
    }

    suspend fun extractText(imagePaths: List<String>): List<OCRResult> {
        val results = mutableListOf<OCRResult>()
        
        for ((index, imagePath) in imagePaths.withIndex()) {
            try {
                val bitmap = BitmapFactory.decodeFile(imagePath)
                if (bitmap != null) {
                    val inputImage = InputImage.fromBitmap(bitmap, 0)
                    val result = recognizer.process(inputImage).addOnSuccessListener { visionText ->
                        val extractedText = visionText.text
                        val confidence = calculateConfidence(visionText)
                        
                        results.add(OCRResult(
                            text = extractedText,
                            imagePath = imagePath,
                            confidence = confidence
                        ))
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        
        return results
    }

    private fun calculateConfidence(visionText: com.google.mlkit.vision.text.Text): Float {
        if (visionText.textBlocks.isEmpty()) return 0f
        
        var totalConfidence = 0f
        var count = 0
        
        for (block in visionText.textBlocks) {
            for (line in block.lines) {
                for (element in line.elements) {
                    totalConfidence += element.confidence
                    count++
                }
            }
        }
        
        return if (count > 0) totalConfidence / count else 0f
    }
}
