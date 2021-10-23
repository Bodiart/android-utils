package io.github.bodiart.utils.extensions

import android.view.animation.Animation

/**
 * Set animation listener.
 * Used just for minimize code
 * @param startCallback - anim start callback
 * @param repeatCallback - anim repeat callback
 * @param endCallback - anim end callback
 */
fun Animation.setListener(
    startCallback: (() -> Unit)? = null,
    repeatCallback: (() -> Unit)? = null,
    endCallback: (() -> Unit)? = null
) {
    this.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationRepeat(animation: Animation?) {
            repeatCallback?.invoke()
        }

        override fun onAnimationEnd(animation: Animation?) {
            endCallback?.invoke()
        }

        override fun onAnimationStart(animation: Animation?) {
            startCallback?.invoke()
        }
    })
}