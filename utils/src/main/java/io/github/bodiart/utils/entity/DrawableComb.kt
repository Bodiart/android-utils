package io.github.bodiart.utils.entity

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

/**
 * Helper class to represent drawable that should be displayed by UI View.
 */
sealed class DrawableComb {
    abstract fun buildDrawable(context: Context): Drawable?

    fun applyTo(imageView: ImageView) {
        imageView.setImageDrawable(buildDrawable(imageView.context))
    }

    data class ResId(@DrawableRes val id: Int) : DrawableComb() {
        override fun buildDrawable(context: Context): Drawable? =
            ContextCompat.getDrawable(context, id)
    }

    data class Draw(val drawable: Drawable) : DrawableComb() {
        override fun buildDrawable(context: Context): Drawable = drawable
    }
}