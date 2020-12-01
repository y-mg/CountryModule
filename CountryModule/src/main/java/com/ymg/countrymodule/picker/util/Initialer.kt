package com.ymg.countrymodule.picker.util


import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import com.ymg.countrymodule.picker.CountryPicker
import com.ymg.countrymodule.picker.CountryPicker.KO
import com.ymg.countrymodule.picker.CountryPickerModel



object Initialer {

    /**
     * Initial 설정(ㄱ ... ㅎ, A ... Z) - 검색 기능 없는 경우
     */
    fun setInitial(
        textInitial: AppCompatTextView,
        items: List<CountryPickerModel>,
        position: Int
    ) {
        val countryPickerModel = items[position]

        when (CountryPicker.getLocale()) {
            // 한국
            KO -> {
                val initial = Searcher.getHangulConsonant(countryPickerModel.name[0]).toString()

                when {
                    position > 0 -> {
                        val beforeData = items[position - 1]
                        val beforeInitial = Searcher.getHangulConsonant(beforeData.name[0]).toString()

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

            // 그 외
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
     * Initial 설정(ㄱ ... ㅎ, A ... Z) - 검색 기능 있는 경우
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
            // 한국
            KO -> {
                val initial = Searcher.getHangulConsonant(countries.name[0]).toString()

                when {
                    position > 0 -> {
                        val beforeData = items[position - 1]
                        val beforeInitial = Searcher.getHangulConsonant(beforeData.name[0]).toString()

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
                                Searcher.getHangulConsonant(items[position].name[0]).toString()
                            }
                        }
                    }
                }
            }

            // 그 외
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