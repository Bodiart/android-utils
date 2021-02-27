package com.bodiart.utils

import android.content.Context
import android.os.Parcelable
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import kotlinx.android.parcel.Parcelize

/**
 * Helper class to represent color that should be displayed by UI View.
 */
sealed class ColorComb : Parcelable {
    @ColorInt
    abstract fun buildColor(context: Context): Int

    @Parcelize
    data class ResId(@ColorRes val id: Int) : ColorComb() {
        override fun buildColor(context: Context): Int =
            ContextCompat.getColor(context, id)
    }

    @Parcelize
    data class Str(@ColorInt val color: Int) : ColorComb() {
        override fun buildColor(context: Context): Int = color
    }
}