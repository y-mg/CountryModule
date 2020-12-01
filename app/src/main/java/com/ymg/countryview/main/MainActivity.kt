package com.ymg.countryview.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.ymg.countrymodule.picker.CountryPicker
import com.ymg.countrymodule.picker.CountryPicker.KO
import com.ymg.countrymodule.picker.CountryPickerModel
import com.ymg.countrymodule.picker.util.Initialer
import com.ymg.countryview.R
import com.ymg.countryview.databinding.ActivityMainBinding
import com.ymg.countryview.databinding.ActivityMainItemBinding


class MainActivity : BasicActivity() {

    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var countryListItems: MutableList<CountryPickerModel>



    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)


        CountryPicker.setLocale(KO)
        //CountryPicker.setLocale(EN)
        countryListItems = CountryPicker.getLoadCountries(this)


        viewBinding.recyclerView.adapter?.run {
            if (this is MainAdapter) {
                this.items = countryListItems
                this.notifyDataSetChanged()
            }
        } ?: run {
            MainAdapter(
                countryListItems
            ).apply {
                viewBinding.recyclerView.adapter = this
            }
        }


        setSearch(viewBinding.editSearch, countryListItems)


        // 구분선
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        decoration.setDrawable(resources.getDrawable(R.drawable.recyclerview_divider, null))
        viewBinding.recyclerView.addItemDecoration(decoration)
    }



    /**
     * Search 설정
     */
    private fun setSearch(
        editSearch: AppCompatEditText,
        items: List<CountryPickerModel>
    ) {
        editSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun afterTextChanged(s: Editable?) { }

            @SuppressLint("DefaultLocale")
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val editSearchValue = s.toString()
                val filteredCountryLocals: MutableList<CountryPickerModel> = mutableListOf()

                items.forEach { country ->
                    if (country.name.contains(editSearchValue.toUpperCase())) {
                        filteredCountryLocals.add(country)
                    }
                }

                viewBinding.recyclerView.adapter?.run {
                    if (this is MainAdapter) {
                        this.items = filteredCountryLocals
                        this.notifyDataSetChanged()
                    }
                }
            }
        })
    }





    inner class MainAdapter(
        var items: List<CountryPickerModel> = arrayListOf()
    ) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val viewBinding = ActivityMainItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(viewBinding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val countryPickerModel = items[position]

            //Initialer.setInitial(holder.textInitial, items, position)
            Initialer.setInitial(holder.textInitial, viewBinding.editSearch.text.toString(), items, position)

            setFlag(holder.textFlag, countryPickerModel.name_code)

            setCountryInfo(holder.textCountryInfo, countryPickerModel.name, countryPickerModel.number_code)
        }

        override fun getItemCount(): Int {
            return items.size
        }



        inner class ViewHolder(viewBinding: ActivityMainItemBinding) : RecyclerView.ViewHolder(viewBinding.root) {
            val textInitial: AppCompatTextView = viewBinding.textInitial
            val textFlag: AppCompatTextView = viewBinding.textFlag
            val textCountryInfo: AppCompatTextView = viewBinding.textCountryInfo
        }
    }



    /**
     * Flag(국기) 설정
     */
    private fun setFlag(
        textFlag: AppCompatTextView,
        nameCode: String
    ) {
        textFlag.text = CountryPicker.getCountryFlag(nameCode)
    }



    /**
     * 국가 정보 설정
     */
    @SuppressLint("SetTextI18n")
    private fun setCountryInfo(
        textCountryInfo: AppCompatTextView,
        name: String,
        numberCode: String
    ) {
        textCountryInfo.text = "$name (+${numberCode})"
    }
}