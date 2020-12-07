package com.ymg.countrymodule.picker

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable
import java.text.Collator



/**
 * @author y-mg
 *
 * 국가 정보를 담을 Data 클래스입니다.
 * This is a data class to hold country information.
 */
@Parcelize
data class CountryPickerModel (

    // Country Name
    val name: String,

    // Country Name Code
    val name_code: String,

    // Country Number Code
    var number_code: String

) : Comparable<CountryPickerModel>, Parcelable, Serializable {
    override operator fun compareTo(other: CountryPickerModel) = Collator.getInstance().compare(name, other.name)
}