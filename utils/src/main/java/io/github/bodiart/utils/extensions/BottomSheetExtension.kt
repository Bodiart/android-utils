package io.github.bodiart.utils.extensions

import android.view.View
import com.google.android.material.bottomsheet.BottomSheetBehavior

/**
 * Add callback to bottom sheet
 * @param onSlide - on bottom sheet slide callback or null
 * @param onStateChanged - on bottom sheet state changed or null
 */
fun BottomSheetBehavior<*>.addCallback(
    onSlide: ((slideOffset: Float) -> Unit)?,
    onStateChanged: ((bottomSheet: View, newState: Int) -> Unit)?
) {
    this.addBottomSheetCallback(object: BottomSheetBehavior.BottomSheetCallback() {
        override fun onSlide(bottomSheet: View, slideOffset: Float) {
            onSlide?.invoke(slideOffset)
        }

        override fun onStateChanged(bottomSheet: View, newState: Int) {
            onStateChanged?.invoke(bottomSheet, newState)
        }
    })
}