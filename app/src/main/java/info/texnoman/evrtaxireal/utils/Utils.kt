package info.texnoman.evrtaxireal.utils

import android.content.Context
import android.content.SharedPreferences
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import info.texnoman.evrtaxireal.R
import info.texnoman.evrtaxireal._user.model.response.RegionResponse
import info.texnoman.evrtaxireal.databinding.FragmentSetOrderBinding
import info.texnoman.evrtaxireal.utils.maskeditText.MaskedEditText

fun ImageView.setImage(drawable: Int) {
    this.setImageResource(drawable)
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.showKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun MaskedEditText.replace(): String {
    return this.text.toString().replace("-", " ")
}

fun SharedPreferences.put(key: String, value: String?) {
    edit().putString(key, value).apply()
}

fun SharedPreferences.put(key: String, value: Int) {
    edit().putInt(key, value).apply()
}

fun SharedPreferences.put(key: String, value: Long) {
    edit().putLong(key, value).apply()
}

fun SharedPreferences.put(key: String, value: Boolean) {
    edit().putBoolean(key, value).apply()
}

fun TextView.changeWhiteColor() {
    this.setTextColor(ContextCompat.getColor(context, R.color.white))
}

fun TextView.changeBlackColor() {

    this.setTextColor(ContextCompat.getColor(context, R.color.textcolor))
}

fun LinearLayoutCompat.changeGreyBackground() {
    this.background = ContextCompat.getDrawable(context, R.drawable.language_background)
}

fun LinearLayoutCompat.changeGreenBackground() {
    this.background = ContextCompat.getDrawable(context, R.drawable.language_flat_selector)
}

fun changeColorBackGround(
    layout1: LinearLayoutCompat,
    layout2: LinearLayoutCompat,
    layout3: LinearLayoutCompat
) {
    layout1.changeGreenBackground()
    layout2.changeGreyBackground()
    layout3.changeGreyBackground()
}

fun changeTextColor(text1: TextView, text2: TextView, text3: TextView) {
    text1.changeWhiteColor()
    text2.changeBlackColor()
    text3.changeBlackColor()
}

fun List<String?>.getErrorText(): String {
    return if (this[0] == null) {
        ""
    } else {
        this[0].toString()
    }
}

fun TextView.error(text: String) {
    this.visibility = View.VISIBLE
    this.text = text
}

fun Int.toNumberFormatString(): String {
    var result = ""
    var n = 0
    this.toString().reversed().forEach { char ->
        if (n % 3 == 0 && n != 0)
            result = " $result"
        result = char + result
        n++
    }
    return result
}

fun getRegionNameList(data: ArrayList<RegionResponse>): ArrayList<String> {
    var list = ArrayList<String>()
    for (i in data.indices) {
        list.add(data[i].name.toString())
    }
    return list
}
fun getRegionId(data: ArrayList<RegionResponse>, position: Int): Int {
    return data[position].id?.toInt()!!
}
fun  NumberPicker(binding: FragmentSetOrderBinding){
     var list = arrayListOf<String>("1","2","3","4","+4")
    var position =2
    setTextNumberPicker(position,list,binding)
    binding.decrease.setOnClickListener {
        if (position>0){
            position--
            setTextNumberPicker(position,list,binding)
        }
    }
    binding.increase.setOnClickListener {
        if (position<4){
            position++
            setTextNumberPicker(position,list,binding)
        }

    }

}

fun RadioButton.changeColor(color:Int){
    this.setTextColor(ContextCompat.getColor(context,color))
}
fun setTextNumberPicker(position: Int, list: ArrayList<String>, binding: FragmentSetOrderBinding) {
    if (position==0){
        binding.tvCenter.text =list[position]
        binding.tvLeft.gone()
    }else{
        binding.tvLeft.text=list[position-1]
        binding.tvLeft.visible()
    }
    binding.tvCenter.text =list[position]
    if (position==list.size-1){
        binding.tvCenter.text =list[position]
        binding.tvRight.gone()
    }else{
        binding.tvRight.text =list[position+1]
        binding.tvRight.visible()
    }
}