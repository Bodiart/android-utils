@file:Suppress("unused")

package io.github.bodiart.utils.entity

import android.content.Context
import android.os.Parcelable
import android.widget.TextView
import androidx.annotation.StringRes
import io.github.bodiart.utils.util.buildSpannable
import kotlinx.android.parcel.Parcelize

/**
 * Helper class to represent text that should be displayed by UI View.
 */
sealed class TextComb : Parcelable {
    abstract fun buildCharSequence(
        context: Context,
        spanFactoryList: List<(Int) -> Any> = emptyList()
    ): CharSequence

    fun applyTo(textView: TextView, spanFactoryList: List<(Int) -> Any> = emptyList()) {
        textView.text = buildCharSequence(textView.context, spanFactoryList)
    }

    @Parcelize
    data class ResId(@StringRes val id: Int) : TextComb() {
        override fun buildCharSequence(
            context: Context,
            spanFactoryList: List<(Int) -> Any>
        ): CharSequence = context.getString(id)
    }

    @Parcelize
    data class Str(val s: String) : TextComb() {
        override fun buildCharSequence(
            context: Context,
            spanFactoryList: List<(Int) -> Any>
        ): CharSequence = s
    }

    @Parcelize
    data class Spannable(
        val template: TextComb,
        val spanList: List<TextComb>
    ) : TextComb() {
        override fun buildCharSequence(
            context: Context,
            spanFactoryList: List<(Int) -> Any>
        ): CharSequence = buildSpannable(
            template.buildCharSequence(context),
            spanList.map { it.buildCharSequence(context) },
            spanFactoryList
        )
    }
}