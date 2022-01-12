package io.github.bodiart.utils.extensions

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.WindowInsets.Type.statusBars
import android.view.WindowInsetsController
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
 * Enables or disables light status bar
 * @param enabled - is light status bar enabled
 */
fun Activity.lightStatusBarSetEnabled(enabled: Boolean) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        window?.insetsController?.setSystemBarsAppearance(
            if (enabled) WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS else 0,
            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
        )
    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { // for older versions
        @Suppress("DEPRECATION")
        window?.decorView?.systemUiVisibility?.let { currentFlags ->
            val flags = if (enabled) {
                currentFlags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                if ((currentFlags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR) != 0) { // is light status bar right now
                    currentFlags xor View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                } else {
                    currentFlags
                }
            }
            window?.decorView?.systemUiVisibility = flags
        }
    }
}

/**
 * Enables or disables light navigation bar
 * @param enabled - is light navigation bar enabled
 */
fun Activity.lightNavigationBarSetEnabled(enabled: Boolean) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        window?.insetsController?.setSystemBarsAppearance(
            if (enabled) WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS else 0,
            WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS
        )
    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { // for older versions
        @Suppress("DEPRECATION")
        window?.decorView?.systemUiVisibility?.let { currentFlags ->
            val flags = if (enabled) {
                currentFlags or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
            } else {
                if ((currentFlags and View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR) != 0) { // is light nav bar right now
                    currentFlags xor View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
                } else {
                    currentFlags
                }
            }
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