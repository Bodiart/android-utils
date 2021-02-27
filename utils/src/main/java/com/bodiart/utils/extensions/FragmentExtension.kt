package com.bodiart.utils.extensions

import android.view.View
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.bodiart.utils.TextComb
import com.google.android.material.snackbar.Snackbar

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