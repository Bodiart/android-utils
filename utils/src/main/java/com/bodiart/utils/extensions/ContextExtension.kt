package com.bodiart.utils.extensions

import android.content.Context
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun Context.getColorCompat(@ColorRes colorResId: Int) = ContextCompat.getColor(this, colorResId)