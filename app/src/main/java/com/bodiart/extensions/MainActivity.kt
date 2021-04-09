package com.bodiart.extensions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bodiart.utils.extensions.lightStatusBar
import com.bodiart.utils.extensions.showContentBehindStatusBar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showContentBehindStatusBar()
        lightStatusBar()
    }
}