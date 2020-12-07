package com.ymg.countrymodule.picker.util



/**
 * @author y-mg
 * 이것은 한글 관련 Object 클래스입니다.
 * This is an object class related to Hangul.
 */
object HangulUtil {

    // 자음 시작 = '가'
    // consonant initiation = '가'
    private const val HANGUL_BEGIN_UNICODE: Char = 44032.toChar()

    // 각 자음마다 가지고 있는 글자 단위 수
    // Number of units of letters in each consonant
    private const val HANGUL_BASE_UNIT: Char = 588.toChar()

    // 자음
    // consonant
    private val HANGUL_CONSONANT =  charArrayOf('ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ')



    // Return Hangul consonant
    internal fun getHangulConsonant(c: Char): Char {
        val hangulBeginUnicode = c - HANGUL_BEGIN_UNICODE
        val index = hangulBeginUnicode / HANGUL_BASE_UNIT.toInt()

        return HANGUL_CONSONANT[index]
    }
}