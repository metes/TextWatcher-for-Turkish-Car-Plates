package com.turkishcarplate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kia.kiafan.utils.textwatcher.PlateTextWatcher
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtPlateNumber.addTextChangedListener(PlateTextWatcher(edtPlateNumber))
    }
}
