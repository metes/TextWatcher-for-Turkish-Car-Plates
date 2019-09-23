package com.kia.kiafan.utils.textwatcher

import android.widget.EditText
import com.turkishcarplate.textwatcher.TextViewListener

class PlateTextWatcher(private val textView: EditText) : TextViewListener() {

    private var wasEdited = false

    private fun convertText(s: String, isDelete: Boolean): CharSequence? {
        // ilk iki karakteri numara olmasi icin zorla
        if (s.length == 2 || s.length == 1) {
            if (!isFullNumber(s)) {
                return removeLast(s)
            }
            // ilk iki karakter numara ise 3. karaktere bosluk ekle
            if (s.length == 2) {
                return "$s "
            }
        }
        if (s.length == 3 && isDelete) {
            return s.substring(0, 2)
        } else if (s.length == 4) {// 4. karakteri harf olmasi icin zorla
            if (isFullNumber(s[3].toString())) {
                return removeLast(s)
            }
        } else if (s.length > 4) {
            // 2 bosluk yoksa
            if (!haveTwoSpace(s)) {
                // 2. bolumde rakam girildi mi
                var fistCharIndexOfLastPart = 0
                s.substring(2).forEachIndexed { index, c ->
                    if (isFullNumber(c.toString())) {
                        fistCharIndexOfLastPart = index
                        return@forEachIndexed
                    }
                }
                // girildi ise 3. bolumu olusturmak icin rakamin onune bosluk ekle
                if (fistCharIndexOfLastPart > 0) {
                    return s.substring(0, s.length - 1) + " " + s.substring(s.length - 1)
                }
            } else {
                // 2 bosluk varsa(3. bolumde ise)
                if (!isFullNumber(s.substring(s.lastIndexOf(' ')).replace(" ", ""))) {
                    return removeLast(s)
                }
            }
        }
        return s
    }

    private fun isFullNumber(text: String): Boolean {
        text.toCharArray().forEach {
            if (!Character.isDigit(it)) {
                return false
            }
        }
        return true
    }

    private fun haveTwoSpace(text: String): Boolean {
        var count = 0
        text.forEach { if (it == ' ') count++ }
        return count > 1
    }

    private fun removeLast(text: String): String {
        return text.substring(0, text.length - 1)
    }

    override fun onTextChanged(before: String, old: String, aNew: String, after: String) {
        if (wasEdited) {
            wasEdited = false
            return
        }
        // get entered value (if required)
        val enteredValue = textView.text.toString()
        val newValue = convertText(enteredValue, old.isNotEmpty() && aNew.isEmpty())
        // don't get trap into infinite loop
        wasEdited = true
        // just replace entered value with whatever you want
        textView.setText(newValue)
        textView.setSelection(newValue?.length ?: 0)
    }

}

