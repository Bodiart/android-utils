package io.github.bodiart

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import io.github.bodiart.extensions.R
import io.github.bodiart.utils.extensions.getStatusBarHeight
import io.github.bodiart.utils.extensions.lightStatusBar
import io.github.bodiart.utils.extensions.setHeight
import io.github.bodiart.utils.extensions.showContentBehindStatusBar


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showContentBehindStatusBar()
        lightStatusBar()
        actionBar?.setBackgroundDrawable(null)
        actionBar?.hide()
        setActionBar(null)
        getStatusBarHeight(findViewById(R.id.root)) { height ->
            findViewById<View>(R.id.statusBarImitation).setHeight(height)
        }
    }
}