package com.ymg.countrymodule.picker.util

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import com.ymg.countrymodule.picker.CountryPicker
import com.ymg.countrymodule.picker.CountryPicker.KO
import com.ymg.countrymodule.picker.CountryPickerModel



/**
 * @author y-mg
 *
 * 이것은 Initial 을 설정할 수 있는 Object 클래스입니다.
 * This is an Object Class that allows you to set Initial.
 */
object Initialer {

    /**
     * - 검색 기능이 없는 경우 Initial 을 설정한다.(ㄱ...ㅎ, A...Z)
     * - If there is no search function, set the initials.(ㄱ...ㅎ, A...Z)
     *
     * @param textInitial -> This is TextView to bind your initials.
     *
     * @param items -> Country List
     *
     * @param position -> Position of country list items
     */
    fun setInitial(
        textInitial: AppCompatTextView,
        items: List<CountryPickerModel>,
        position: Int
    ) {
        val countryPickerModel = items[position]

        when (CountryPicker.getLocale()) {
            // Korean
            KO -> {
                val initial = HangulUtil.getHangulConsonant(countryPickerModel.name[0]).toString()

                when {
                    position > 0 -> {
                        val beforeData = items[position - 1]
                        val beforeInitial = HangulUtil.getHangulConsonant(beforeData.name[0]).toString()

                        when {
                            beforeInitial != initial -> {
                                textInitial.visibility = View.VISIBLE
                                textInitial.text = initial
                            }

                            else -> {
                                textInitial.visibility = View.GONE
                            }
                        }
                    }

                    position == 0 -> {
                        textInitial.visibility = View.VISIBLE
                        textInitial.text = initial
                    }
                }
            }

            // English
            else -> {
                val initial = countryPickerModel.name.substring(0, 1)

                when {
                    position > 0 -> {
                        val beforeData = items[position - 1]
                        val beforeInitial = beforeData.name.substring(0, 1)

                        when {
                            beforeInitial != initial -> {
                                textInitial.visibility = View.VISIBLE
                                textInitial.text = initial
                            }

                            else -> {
                                textInitial.visibility = View.GONE
                            }
                        }
                    }

                    position == 0 -> {
                        textInitial.visibility = View.VISIBLE
                        textInitial.text = initial
                    }
                }
            }
        }
    }



    /**
     * - 검색 기능이 있는 경우 Initial 을 설정한다.(ㄱ...ㅎ, A...Z)
     * - If there is search function, set the initials.(ㄱ...ㅎ, A...Z)
     *
     * @param textInitial -> This is TextView to bind your initials.
     *
     * @param editSearch -> This is a search word
     *
     * @param items -> Country List
     *
     * @param position -> Position of country list items
     */
    fun setInitial(
        textInitial: AppCompatTextView,
        editSearch: String,
        items: List<CountryPickerModel>,
        position: Int
    ) {
        val editSearchValue = editSearch.trim()
        val countries = items[position]

        when (CountryPicker.getLocale()) {
            // Korean
            KO -> {
                val initial = HangulUtil.getHangulConsonant(countries.name[0]).toString()

                when {
                    position > 0 -> {
                        val beforeData = items[position - 1]
                        val beforeInitial = HangulUtil.getHangulConsonant(beforeData.name[0]).toString()

                        when {
                            beforeInitial != initial -> {
                                textInitial.visibility = View.VISIBLE
                                textInitial.text = initial
                            }

                            else -> {
                                textInitial.visibility = View.GONE
                            }
                        }
                    }

                    position == 0 -> {
                        textInitial.visibility = View.VISIBLE

                        textInitial.text = when {
                            editSearchValue.isEmpty() -> {
                                initial
                            }

                            else -> {
                                HangulUtil.getHangulConsonant(items[position].name[0]).toString()
                            }
                        }
                    }
                }
            }

            // English
            else -> {
                val initial = countries.name.substring(0, 1)

                when {
                    position > 0 -> {
                        val beforeData = items[position - 1]
                        val beforeInitial = beforeData.name.substring(0, 1)

                        when {
                            beforeInitial != initial -> {
                                textInitial.visibility = View.VISIBLE
                                textInitial.text = initial
                            }

                            else -> {
                                textInitial.visibility = View.GONE
                            }
                        }
                    }

                    position == 0 -> {
                        textInitial.visibility = View.VISIBLE

                        textInitial.text = when {
                            editSearchValue.isEmpty() -> {
                                initial
                            }

                            else -> {
                                items[position].name.substring(0, 1)
                            }
                        }
                    }
                }
            }
        }
    }
}