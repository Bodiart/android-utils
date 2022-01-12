package io.github.bodiart

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import io.github.bodiart.extensions.R
import io.github.bodiart.utils.extensions.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showContentBehindStatusBar()
        lightStatusBarSetEnabled(true)

        window?.navigationBarColor = Color.TRANSPARENT
        lightNavigationBarSetEnabled(true)

        getStatusBarHeight(findViewById(R.id.root)) { height ->
            findViewById<View>(R.id.statusBarImitation).setHeight(height)
        }

        findViewById<View>(R.id.viewForAnim).startAnimation(AnimationUtils.loadAnimation(this, R.anim.test_anim))
        findViewById<View>(R.id.viewForAnim2).startAnimation(AnimationUtils.loadAnimation(this, R.anim.test_anim2))

    }
}