package com.ymg.countrymodule.picker

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.telephony.TelephonyManager
import com.ymg.countrymodule.R
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.util.*


object CountryPicker {

    const val KO = "ko" // 한국
    const val EN = "en" // 영어권



    // Locale
    private var locale = KO

    // 국가 기본값 KO, EN
    private val DEFAULT_COUNTRY_KO =
        CountryPickerModel(
            "대한민국",
            "kr",
            "82"
        )
    private val DEFAULT_COUNTRY_EN =
        CountryPickerModel(
            "South Korea",
            "kr",
            "82"
        )



    /**
     * 지역 설정
     */
    fun setLocale(locale: String) {
        this.locale = locale
    }

    /**
     * 설정된 지역 값
     */
    fun getLocale(): String {
        return locale
    }



    /**
     * 국가 찾기
     */
    fun getDetectedCountry(context: Context): CountryPickerModel? {
        var countryPickerModel: CountryPickerModel? =
            getDetectSIMCountry(
                context
            )

        if (countryPickerModel == null) {
            countryPickerModel =
                getDetectNetworkCountry(
                    context
                )
        }

        if (countryPickerModel == null) {
            countryPickerModel =
                getDetectLocaleCountry(
                    context
                )
        }

        if (countryPickerModel == null) {
            countryPickerModel = when (locale) {
                // 한국
                KO -> {
                    DEFAULT_COUNTRY_KO
                }

                // 나머지
                else -> {
                    DEFAULT_COUNTRY_EN
                }
            }
        }

        return countryPickerModel
    }



    /**
     * 국기 가져오기
     */
    @SuppressLint("DefaultLocale")
    fun getCountryFlag(nameCode: String): String {
        val flagOffset = 0x1F1E6
        val asciiOffset = 0x41

        val firstChar = Character.codePointAt(nameCode.toUpperCase(), 0) - asciiOffset + flagOffset
        val secondChar = Character.codePointAt(nameCode.toUpperCase(), 1) - asciiOffset + flagOffset

        return String(Character.toChars(firstChar)) + String(Character.toChars(secondChar))
    }



    /**
     * SIM 기반 국가 정보
     */
    private fun getDetectSIMCountry(context: Context): CountryPickerModel? {
        val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val simCountryISO: String? = telephonyManager.simCountryIso

        return when {
            simCountryISO.isNullOrEmpty() -> {
                null
            }

            else -> {
                getCountryCode(
                    context,
                    simCountryISO
                )
            }
        }
    }



    /**
     * Network 기반 국가정보
     */
    private fun getDetectNetworkCountry(context: Context): CountryPickerModel? {
        val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val networkCountryISO: String? = telephonyManager.networkCountryIso

        return when {
            networkCountryISO.isNullOrEmpty() -> {
                null
            }

            else -> {
                getCountryCode(
                    context,
                    networkCountryISO
                )
            }
        }
    }



    /**
     * Locale 기반 국가정보
     */
    private fun getDetectLocaleCountry(context: Context): CountryPickerModel? {
        val localeCountryISO: String? = getCurrentLocale(context).country

        return when {
            localeCountryISO.isNullOrEmpty() -> {
                null
            }

            else -> {
                getCountryCode(
                    context,
                    localeCountryISO
                )
            }
        }
    }

    @Suppress("DEPRECATION")
    fun getCurrentLocale(context: Context): Locale {
        return when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> {
                context.resources.configuration.locales.get(0)
            }

            else -> {
                context.resources.configuration.locale
            }
        }
    }



    /**
     * 국가 코드 가져오기
     */
    private fun getCountryCode(
        context: Context,
        nameCode: String
    ): CountryPickerModel? {
        val countries =
            getLoadCountries(
                context
            )

        for (country in countries) {
            if (country.name_code == nameCode) {
                return country
            }
        }
        return null
    }



    /**
     * raw 파일에서 파싱
     */
    @SuppressLint("DefaultLocale")
    fun getLoadCountries(context: Context): MutableList<CountryPickerModel> {
        val countries = mutableListOf<CountryPickerModel>()

        val inputStream = when (locale) {
            // 한국
            KO -> {
                context.resources.openRawResource(R.raw.country_ko)
            }

            // 나머지
            else -> {
                context.resources.openRawResource(R.raw.country_en)
            }
        }


        val xmlPullParser = XmlPullParserFactory.newInstance().newPullParser()
        xmlPullParser.setInput(inputStream, "UTF-8")


        var event = xmlPullParser.eventType


        while (event != XmlPullParser.END_DOCUMENT) {
            if (event == XmlPullParser.END_TAG && xmlPullParser.name == "country") {
                val ccpCountry =
                    CountryPickerModel(
                        xmlPullParser.getAttributeValue(null, "name").toUpperCase(),
                        xmlPullParser.getAttributeValue(null, "name_code"),
                        xmlPullParser.getAttributeValue(null, "number_code")
                    )
                countries.add(ccpCountry)
            }
            event = xmlPullParser.next()
        }


        inputStream.close()

        // Sort List
        countries.sort()
        return countries
    }
}