package com.bodiart.utils.extensions

import android.content.Context
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import com.bodiart.utils.ColorComb

/**
 * Set drawable color from resource
 * @param context - [Context]
 * @param colorResId - color resource id
 */
fun Drawable.setColorRes(context: Context, @ColorRes colorResId: Int) {
    val color = colorResId.getColor(context)

    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
        colorFilter = BlendModeColorFilter(color, BlendMode.SRC_IN)
    } else {
        @Suppress("DEPRECATION")
        setColorFilter(color, PorterDuff.Mode.SRC_IN)
    }
}

/**
 * Set drawable color by color int
 * @param context - [Context]
 * @param colorInt - color int value
 */
fun Drawable.setColorInt(context: Context, @ColorInt colorInt: Int) {

    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
        colorFilter = BlendModeColorFilter(colorInt, BlendMode.COLOR)
    } else {
        @Suppress("DEPRECATION")
        setColorFilter(colorInt, PorterDuff.Mode.SRC_IN)
    }
}

/**
 * Set drawable color
 * @param context - [Context]
 * @param color - [ColorComb]
 */
fun Drawable.setColor(context: Context, color: ColorComb) {
    val colorInt = color.buildColor(context)
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
        colorFilter = BlendModeColorFilter(colorInt, BlendMode.COLOR)
    } else {
        @Suppress("DEPRECATION")
        setColorFilter(colorInt, PorterDuff.Mode.SRC_IN)
    }
}