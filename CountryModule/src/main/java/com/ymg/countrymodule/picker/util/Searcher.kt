package com.ymg.countrymodule.picker.util

object Searcher {

    // 자음 시작 = '가'
    private const val HANGUL_BEGIN_UNICODE: Char = 44032.toChar() // 가

    // 각 자음마다 가지고 있는 글자 단위 수
    private const val HANGUL_BASE_UNIT: Char = 588.toChar()

    // 자음
    private val HANGUL_CONSONANT =  charArrayOf('ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ')



    fun getHangulConsonant(c: Char): Char {
        val hangulBeginUnicode = c - HANGUL_BEGIN_UNICODE
        val index = hangulBeginUnicode / HANGUL_BASE_UNIT.toInt()

        return HANGUL_CONSONANT[index]
    }
}