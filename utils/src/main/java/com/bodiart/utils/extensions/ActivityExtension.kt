package com.bodiart.utils.extensions

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.WindowInsets.Type.statusBars
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.view.doOnLayout
import com.bodiart.utils.TextComb

/**
 * Make toast from activity
 * @param text - toast text
 * @param duration - toast duration, default is [Toast.LENGTH_SHORT]
 */
fun Activity.toast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, text, duration).show()

/**
 * Make toast from activity
 * @param textResId - toast text resource id
 * @param duration - toast duration, default is [Toast.LENGTH_SHORT]
 */
fun Activity.toast(@StringRes textResId: Int, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, textResId, duration).show()

/**
 * Make toast from activity
 * @param textComb - [TextComb] for toast
 * @param duration - toast duration, default is [Toast.LENGTH_SHORT]
 */
fun Activity.toast(textComb: TextComb, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, textComb.buildCharSequence(this), duration).show()

/**
 * Show activity content behind status bar.
 */
@Suppress("DEPRECATION")
fun Activity.showContentBehindStatusBar() {
    window.apply {
        clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        statusBarColor = Color.TRANSPARENT
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            statusBarColor = Color.BLACK
        }
    }
}

/**
 * Get status bar height.
 * Different implementation depends android version
 * @param view - root window [View]
 * @param completeCallback - get height complete callback
 */
fun getStatusBarHeight(view: View, completeCallback: (Int) -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        view.doOnLayout {
            val statusBarHeight = view.rootWindowInsets?.getInsets(statusBars())?.top ?: 0
            completeCallback(statusBarHeight)
        }
    } else {
        @Suppress("DEPRECATION")
        view.setOnApplyWindowInsetsListener { _, insets ->
            val statusBarHeight = insets.systemWindowInsetTop
            completeCallback(statusBarHeight)
            insets
        }
    }
}