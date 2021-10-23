package io.github.bodiart.extensions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bodiart.extensions.R
import io.github.bodiart.utils.extensions.lightStatusBar
import io.github.bodiart.utils.extensions.showContentBehindStatusBar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showContentBehindStatusBar()
        lightStatusBar()
    }
}