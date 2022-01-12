package io.github.bodiart.utils.extensions

import android.os.Build
import android.view.View
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import io.github.bodiart.utils.entity.TextComb
import com.google.android.material.snackbar.Snackbar

@Suppress("DEPRECATION")
fun Fragment.lightStatusBar() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        activity?.window?.decorView?.systemUiVisibility?.let { currentFlags ->
            val flags = currentFlags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            activity?.window?.decorView?.systemUiVisibility = flags
        }
    }
}

/**
 * Make snack in fragment
 * @param text - snack text
 * @param view - [View] for make snack, default is fragment root view
 * @param anchorView - [View] for anchor, default is null
 */
fun Fragment.snack(text: String, view: View? = this.view, anchorView: View? = null) {
    view?.let {
        val snackBar = Snackbar.make(view, text, Snackbar.LENGTH_LONG)
        if (anchorView != null)
            snackBar.anchorView = anchorView
        snackBar.show()
    }
}

/**
 * Make snack in fragment
 * @param textRes - snack text resource id
 * @param view - [View] for make snack, default is fragment root view
 * @param anchorView - [View] for anchor, default is null
 */
fun Fragment.snack(@StringRes textRes: Int, view: View? = this.view, anchorView: View? = null) {
    view?.let {
        val snackBar = Snackbar.make(view, textRes, Snackbar.LENGTH_LONG)
        if (anchorView != null)
            snackBar.anchorView = anchorView
        snackBar.show()
    }
}

/**
 * Make snack in fragment
 * @param textComb - [TextComb] for snack
 * @param view - [View] for make snack, default is fragment root view
 * @param anchorView - [View] for anchor, default is null
 */
fun Fragment.snack(textComb: TextComb, view: View? = this.view, anchorView: View? = null) {
    view?.let {
        val snackBar = Snackbar.make(view, textComb.buildCharSequence(it.context), Snackbar.LENGTH_LONG)
        if (anchorView != null)
            snackBar.anchorView = anchorView
        snackBar.show()
    }
}