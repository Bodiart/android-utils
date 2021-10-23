package io.github.bodiart.utils.extensions

import android.widget.TextSwitcher
import io.github.bodiart.utils.TextComb

/**
 * Set text in text switcher
 * @param text - [TextComb]
 * @param animate - animate
 */
fun TextSwitcher.setText(text: TextComb, animate: Boolean) {
    val string = text.buildCharSequence(context)
    if (animate) setText(string) else setCurrentText(string)
}