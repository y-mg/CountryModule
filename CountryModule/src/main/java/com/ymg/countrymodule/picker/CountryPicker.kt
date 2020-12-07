package com.ymg.countrymodule.picker

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.telephony.TelephonyManager
import com.ymg.countrymodule.R
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.util.*



/**
 * @author y-mg
 *
 * 이것은 국가 정보를 가져오는 Object 클래스입니다.
 * This is the Object Class from which the country information is obtained.
 */
object CountryPicker {

    // Korean, English
    const val KO = "ko"
    const val EN = "en"

    // Default Locale
    private var locale = KO

    // Default Locale Information
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
     * - 지역(언어)를 설정한다.
     * - Set up a locale(language).
     *
     * @param locale -> KO(korean) or EN(English)
     */
    fun setLocale(locale: String) {
        this.locale = locale
    }

    /**
     * Return Locale
     */
    internal fun getLocale(): String {
        return locale
    }



    /**
     * - 현재 국가 정보를 찾는다.
     * - Detect current national information.
     *
     * @param context
     */
    fun getDetectedCountry(context: Context): CountryPickerModel {
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
                // Korean
                KO -> {
                    DEFAULT_COUNTRY_KO
                }

                // English
                else -> {
                    DEFAULT_COUNTRY_EN
                }
            }
        }

        return countryPickerModel
    }



    /**
     * - 국기를 가져온다.
     * - Getting the country flag.
     *
     * @param countryNameCode -> Country Name Code(KR, US...)
     */
    @SuppressLint("DefaultLocale")
    fun getCountryFlag(countryNameCode: String): String {
        val flagOffset = 0x1F1E6
        val asciiOffset = 0x41

        val firstChar = Character.codePointAt(countryNameCode.toUpperCase(), 0) - asciiOffset + flagOffset
        val secondChar = Character.codePointAt(countryNameCode.toUpperCase(), 1) - asciiOffset + flagOffset

        return String(Character.toChars(firstChar)) + String(Character.toChars(secondChar))
    }



    /**
     * Return Country information with SIM
     */
    private fun getDetectSIMCountry(context: Context): CountryPickerModel? {
        val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val simCountryISO: String? = telephonyManager.simCountryIso

        return when {
            simCountryISO.isNullOrEmpty() -> {
                null
            }

            else -> {
                getCountryNumberCode(
                    context,
                    simCountryISO
                )
            }
        }
    }



    /**
     * Return Country information with network
     */
    private fun getDetectNetworkCountry(context: Context): CountryPickerModel? {
        val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val networkCountryISO: String? = telephonyManager.networkCountryIso

        return when {
            networkCountryISO.isNullOrEmpty() -> {
                null
            }

            else -> {
                getCountryNumberCode(
                    context,
                    networkCountryISO
                )
            }
        }
    }



    /**
     * Return Country information with device locale
     */
    private fun getDetectLocaleCountry(context: Context): CountryPickerModel? {
        val localeCountryISO: String? = getCurrentLocale(context).country

        return when {
            localeCountryISO.isNullOrEmpty() -> {
                null
            }

            else -> {
                getCountryNumberCode(
                    context,
                    localeCountryISO
                )
            }
        }
    }

    @Suppress("DEPRECATION")
    private fun getCurrentLocale(context: Context): Locale {
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
     * Return Country number code
     */
    private fun getCountryNumberCode(
        context: Context,
        countryNameCode: String
    ): CountryPickerModel? {
        val countries =
            getLoadCountries(
                context
            )

        for (country in countries) {
            if (country.name_code == countryNameCode) {
                return country
            }
        }
        return null
    }



    /**
     * - 국가 리스트를 가져온다.
     * - Getting in a list of countries.
     *
     * @param context
     */
    @SuppressLint("DefaultLocale")
    fun getLoadCountries(context: Context): MutableList<CountryPickerModel> {
        val countries = mutableListOf<CountryPickerModel>()

        val inputStream = when (locale) {
            // Korean
            KO -> {
                context.resources.openRawResource(R.raw.country_ko)
            }

            // English
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

        // Sorting List
        countries.sort()
        return countries
    }
}