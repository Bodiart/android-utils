package com.bodiart.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Parcelable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import kotlinx.android.parcel.Parcelize

/**
 * Helper class to represent drawable that should be displayed by UI View.
 */
sealed class DrawableComb : Parcelable {
    abstract fun buildDrawable(context: Context): Drawable?

    fun applyTo(imageView: ImageView) {
        imageView.setImageDrawable(buildDrawable(imageView.context))
    }

    @Parcelize
    data class ResId(@DrawableRes val id: Int) : DrawableComb() {
        override fun buildDrawable(context: Context): Drawable? =
            ContextCompat.getDrawable(context, id)
    }

    @Parcelize
    data class Draw(val drawable: Drawable) : DrawableComb() {
        override fun buildDrawable(context: Context): Drawable = drawable
    }
}