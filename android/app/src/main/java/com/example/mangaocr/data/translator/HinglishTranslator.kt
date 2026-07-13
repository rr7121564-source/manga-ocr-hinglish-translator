package com.example.mangaocr.data.translator

class HinglishTranslator {

    private val hindiToHinglish = mapOf(
        '\u0915' to "k", '\u0916' to "kh", '\u0917' to "g", '\u0918' to "gh",
        '\u091a' to "ch", '\u091b' to "chh", '\u091c' to "j", '\u091d' to "jh",
        '\u091f' to "t", '\u0920' to "th", '\u0921' to "d", '\u0922' to "dh",
        '\u0924' to "t", '\u0925' to "th", '\u0926' to "d", '\u0927' to "dh",
        '\u092a' to "p", '\u092b' to "ph", '\u092c' to "b", '\u092d' to "bh",
        '\u092f' to "y", '\u0930' to "r", '\u0932' to "l", '\u0935' to "w",
        '\u0936' to "sh", '\u0937' to "sh", '\u0938' to "s", '\u0939' to "h",
        '\u0905' to "a", '\u0906' to "aa", '\u0907' to "i", '\u0908' to "ii",
        '\u0909' to "u", '\u090a' to "uu", '\u090b' to "ri",
        '\u090f' to "e", '\u0910' to "ai", '\u0913' to "o", '\u0914' to "au"
    )

    fun toHinglish(text: String): String {
        val result = StringBuilder()
        for (char in text) {
            result.append(hindiToHinglish[char] ?: char.toString())
        }
        return result.toString()
    }
}
