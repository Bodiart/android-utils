package com.bodiart.utils

import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.bodiart.utils.extensions.invisibleOrGone
import com.bodiart.utils.extensions.setGone
import com.bodiart.utils.extensions.setVisible

// Common view property values
const val VIEW_ALPHA_TRANSPARENT = 0F
const val VIEW_ALPHA_OPAQUE = 1F

// Common view animation durations
const val APPEARANCE_ANIMATION_DURATION = 250L
const val ROTATION_ANIMATION_DURATION = 250L
const val TRANSLATION_ANIMATION_DURATION = 250L


/**
 * Animate view appearing
 * @param view - [View] for animate
 * @param duration - anim duration, default is [APPEARANCE_ANIMATION_DURATION]
 * @param endAction - anim end callback or null
 */
fun animateViewAppearing(
    view: View,
    duration: Long = APPEARANCE_ANIMATION_DURATION,
    endAction: (() -> Unit)? = null
) {
    view.animate().cancel()
    if (view.isGone || view.isInvisible || view.alpha != VIEW_ALPHA_OPAQUE) {
        view.alpha = VIEW_ALPHA_TRANSPARENT
        view.setVisible(true)
        val animator = view.animate().alpha(VIEW_ALPHA_OPAQUE)
        animator.duration = duration
        endAction?.let { animator.withEndAction { it.invoke() } }
    }
}

/**
 * Animate view disapearing
 * @param view - [View] for animate
 * @param duration - anim duration, default is [APPEARANCE_ANIMATION_DURATION]
 * @param goneMode - set view to gone mode on end
 * @param endAction - anim end callback or null
 */
fun animateViewDisappearing(
    view: View,
    duration: Long = APPEARANCE_ANIMATION_DURATION,
    goneMode: Boolean = true,
    endAction: (() -> Unit)? = null
) {
    view.animate().cancel()
    if (view.isVisible) {
        val animator = view.animate().alpha(VIEW_ALPHA_TRANSPARENT).withEndAction {
            view.invisibleOrGone(!goneMode)
            view.alpha = VIEW_ALPHA_OPAQUE
            endAction?.invoke()
        }
        animator.duration = duration
    }
}

/**
 * Change view visibility
 * @param animate - animate visibility
 * @param view - [View] for change visibility
 * @param isVisible - is view should be visible
 * @param duration - anim duration, default is [APPEARANCE_ANIMATION_DURATION],
 * @param goneMode - set view to gone mode after changing visibility
 */
fun changeViewVisibility(
    animate: Boolean,
    view: View,
    isVisible: Boolean,
    duration: Long = APPEARANCE_ANIMATION_DURATION,
    goneMode: Boolean = true
) {
    view.animate().cancel()
    if (view.isVisible != isVisible || view.alpha != VIEW_ALPHA_OPAQUE) {
        if (animate) {
            if (isVisible) {
                animateViewAppearing(view, duration)
            } else {
                animateViewDisappearing(view, duration, goneMode)
            }
        } else {
            view.run { if (goneMode) setGone(isVisible) else setVisible(isVisible) }
        }
    }
}

/**
 * Change view rotation
 * @param animate - animate view rotation
 * @param view - [View]
 * @param rotation - rotation degrees
 * @param disableViewWhenAnimating - disable view when it is animating
 * */
fun changeViewRotation(
    animate: Boolean,
    view: View,
    rotation: Float,
    duration: Long = ROTATION_ANIMATION_DURATION,
    disableViewWhenAnimating: Boolean = false
) {
    view.animate().cancel()
    if (view.rotation == rotation) {
        return
    }

    if (animate) {
        animateViewRotation(view, rotation, duration, disableViewWhenAnimating)
    } else {
        view.rotation = rotation
    }
}

/**
 * Change view scale x and y
 * @param animate - animate changing
 * @param view - [View] fro animate
 * @param scale - scale
 */
fun changeViewScaleXY(animate: Boolean, view: View, scale: Float) {
    view.animate().cancel()
    if (view.scaleX == scale && view.scaleY == scale) {
        return
    }

    if (animate) {
        animateViewScaleXY(view, scale)
    } else {
        view.scaleX = scale
        view.scaleY = scale
    }
}

/**
 * Animate view scale x y
 * @param view - [View] for animate
 * @param scale - scale
 */
fun animateViewScaleXY(view: View, scale: Float) {
    view.animate().cancel()
    view.animate().scaleX(scale)
    view.animate().scaleY(scale)
}

/**
 * Animate view rotation
 * @param view - [View]
 * @param rotation - rotation in degrees
 * @param disableViewWhenAnimating - disable view when it is animating
 * */
fun animateViewRotation(
    view: View,
    rotation: Float,
    duration: Long = ROTATION_ANIMATION_DURATION,
    disableViewWhenAnimating: Boolean
) {
    view.animate().cancel()
    val animator = view.animate().rotation(rotation)
    animator.duration = duration
    animator.withStartAction {
        if (disableViewWhenAnimating)
            view.isEnabled = false
    }
    animator.withEndAction {
        if (disableViewWhenAnimating)
            view.isEnabled = true
    }
}

/**
 * Change view translation X
 * @param animate - animate translation X
 * @param view - [View]
 * @param translationX - translation X
 * */
fun changeViewTranslationX(
    animate: Boolean,
    view: View,
    translationX: Float,
    duration: Long = TRANSLATION_ANIMATION_DURATION
) {
    view.animate().cancel()
    if (animate) {
        animateViewTranslationX(view, translationX, duration)
    } else
        view.translationX = translationX
}

/**
 * Animate view translation X
 * @param view - [View]
 * @param translationX - translation X
 * */
fun animateViewTranslationX(
    view: View,
    translationX: Float,
    duration: Long = TRANSLATION_ANIMATION_DURATION
) {
    view.animate().cancel()
    val animator = view.animate().translationX(translationX)
    animator.duration = duration
    animator.interpolator = DecelerateInterpolator()
}