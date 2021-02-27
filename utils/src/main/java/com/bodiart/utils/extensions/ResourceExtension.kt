package com.bodiart.utils.extensions

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.Html
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager
import com.bodiart.utils.TextComb
import com.google.android.material.bottomsheet.BottomSheetBehavior

/**
 * Get dp from int pixels.
 */
val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

/**
 * Get dp from float pixels.
 */
val Float.dp: Float
    get() = (this * Resources.getSystem().displayMetrics.density)

/**
 * Get dimension
 * @param context - [Context]
 * @return Dimension value
 */
fun Int.getDimen(context: Context): Int = context.resources.getDimensionPixelSize(this)

/**
 * Get drawable
 * @param context - [Context]
 * @return [Drawable] or null
 */
fun Int.getDrawable(context: Context): Drawable? = ContextCompat.getDrawable(context, this)

/**
 * Get color
 * @param context - [Context]
 * @return Color
 */
fun Int.getColor(context: Context): Int = ContextCompat.getColor(context, this)