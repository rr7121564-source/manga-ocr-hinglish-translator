package com.example.mangaocr.data.translator

class HinglishTranslator {

    // Devanagari to Roman script mapping
    private val hindiToHinglish = mapOf(
        // Consonants
        'क' to "k", 'ख' to "kh", 'ग' to "g", 'घ' to "gh", 'ङ' to "ng",
        'च' to "ch", 'छ' to "chh", 'ज' to "j", 'झ' to "jh", 'ञ' to "ny",
        'ट' to "t", 'ठ' to "th", 'ड' to "d", 'ढ' to "dh", 'ण' to "n",
        'त' to "t", 'थ' to "th", 'द' to "d", 'ध' to "dh", 'न' to "n",
        'प' to "p", 'फ' to "ph", 'ब' to "b", 'भ' to "bh", 'म' to "m",
        'य' to "y", 'र' to "r", 'ल' to "l", 'व' to "w",
        'श' to "sh", 'ष' to "sh", 'स' to "s", 'ह' to "h",
        
        // Vowels
        'अ' to "a", 'आ' to "aa", 'इ' to "i", 'ई' to "ii",
        'उ' to "u", 'ऊ' to "uu", 'ऋ' to "ri",
        'ए' to "e", 'ऐ' to "ai", 'ओ' to "o", 'औ' to "au",
        
        // Vowel Signs
        'ा' to "a", 'ि' to "i", 'ी' to "ii", 'ु' to "u", 'ू' to "uu",
        'े' to "e", 'ै' to "ai", 'ो' to "o", 'ौ' to "au",
        
        // Special
        'ँ' to "n", 'ं' to "n", 'ः' to "h", '्' to "",
        '०' to "0", '१' to "1", '२' to "2", '३' to "3", '४' to "4",
        '५' to "5", '६' to "6", '७' to "7", '८' to "8", '९' to "9"
    )

    fun toHinglish(text: String): String {
        val result = StringBuilder()
        
        for (char in text) {
            result.append(hindiToHinglish[char] ?: char.toString())
        }
        
        return result.toString()
    }
}
