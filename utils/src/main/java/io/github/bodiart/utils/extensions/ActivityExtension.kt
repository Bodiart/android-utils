package io.github.bodiart.utils.extensions

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.WindowInsets.Type.statusBars
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.view.doOnLayout
import io.github.bodiart.utils.TextComb

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
    window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    window?.statusBarColor = Color.TRANSPARENT
}

/**
 * Light status bar.
 */
@Suppress("DEPRECATION")
fun Activity.lightStatusBar() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        window?.decorView?.systemUiVisibility?.let { currentFlags ->
            val flags = currentFlags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window?.decorView?.systemUiVisibility = flags
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