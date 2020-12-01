package com.ymg.countrymodule.picker

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable
import java.text.Collator


@Parcelize
data class CountryPickerModel (

    // 국가 이름
    val name: String,

    // 국가 코드
    val name_code: String,

    // 국가 넘버 코드
    var number_code: String

) : Comparable<CountryPickerModel>, Parcelable, Serializable {
    override operator fun compareTo(other: CountryPickerModel) = Collator.getInstance().compare(name, other.name)
}