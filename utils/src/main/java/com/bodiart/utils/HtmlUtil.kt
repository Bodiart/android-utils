package com.bodiart.utils

import android.content.Context
import android.os.Build
import android.text.Html


/**
 * Convert html.
 * Used different implementation depends android version
 * @param html - html text
 */
fun fromHtml(html: String) = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
    Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)
} else {
    @Suppress("DEPRECATION")
    Html.fromHtml(html)
}

/**
 * Convert html.
 * Used different implementation depends android version
 * @param htmlText - [TextComb]
 */
fun fromHtml(context: Context, htmlText: TextComb) =
    fromHtml(htmlText.buildCharSequence(context).toString())