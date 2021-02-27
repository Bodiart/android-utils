package com.bodiart.utils

import android.text.Spannable
import android.text.SpannableStringBuilder


/**
 * Builds indexed string span placeholder.
 *
 * @param spanIndex String span index
 *
 * @return Indexed string span placeholder
 */
fun getSpanPlaceholder(spanIndex: Int) = "\$$spanIndex%s"

/**
 * Builds [Spannable] instance.
 *
 * @param templateText Base template text
 * @param spanTextList List of spans
 * @param spanFactoryList Factories to build spannable spans
 *
 * @return [Spannable] instance
 */
fun buildSpannable(
    templateText: CharSequence,
    spanTextList: List<CharSequence>,
    spanFactoryList: List<(Int) -> Any>
): Spannable {
    val spannableBuilder = SpannableStringBuilder(templateText)
    spanTextList.forEachIndexed { index, spanText ->
        val placeholder = getSpanPlaceholder(index + 1)
        val spanStart = spannableBuilder.indexOf(placeholder)
        if (spanStart >= 0) {
            val placeholderEnd = spanStart + placeholder.length
            val spanEnd = spanStart + spanText.length
            spannableBuilder.replace(spanStart, placeholderEnd, spanText)
            spanFactoryList.forEach { spanFactory ->
                spannableBuilder.setSpan(
                    spanFactory(index),
                    spanStart,
                    spanEnd,
                    Spannable.SPAN_INCLUSIVE_EXCLUSIVE
                )
            }
        }
    }
    return spannableBuilder
}