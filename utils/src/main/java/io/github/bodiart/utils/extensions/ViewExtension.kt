package io.github.bodiart.utils.extensions

import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.AnimRes
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop

/**
 * Set view gone
 * @param isVisible - is view visible (if true), or gone (if false)
 */
fun View.setGone(isVisible: Boolean) {
    this.visibility = if (isVisible) View.VISIBLE else View.GONE
}

/**
 * Set view visible
 * @param isVisible - is view visible (if true), or invisible (if false)
 */
fun View.setVisible(isVisible: Boolean) {
    this.visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
}

/**
 * Is view visible
 * @return is view visible
 */
fun Boolean?.getVisibility(): Int {
    return if (this == true) View.VISIBLE else View.INVISIBLE
}

/**
 * Set view margin start
 * @param marginStart - margin start in pixels
 */
fun View.setMarginStart(marginStart: Int) {
    val menuLayoutParams = this.layoutParams as ViewGroup.MarginLayoutParams
    menuLayoutParams.setMargins(marginStart, marginTop, marginRight, marginBottom)
    this.layoutParams = menuLayoutParams
}

/**
 * Set view margin top
 * @param marginTop - margin top in pixels
 */
fun View.setMarginTop(marginTop: Int) {
    val menuLayoutParams = this.layoutParams as ViewGroup.MarginLayoutParams
    menuLayoutParams.setMargins(marginLeft, marginTop, marginRight, marginBottom)
    this.layoutParams = menuLayoutParams
}

/**
 * Set view margin end
 * @param marginEnd - margin end in pixels
 */
fun View.setMarginEnd(marginEnd: Int) {
    val menuLayoutParams = this.layoutParams as ViewGroup.MarginLayoutParams
    menuLayoutParams.setMargins(marginLeft, marginTop, marginEnd, marginBottom)
    this.layoutParams = menuLayoutParams
}

/**
 * Set view margin bot
 * @param marginBot - margin bot in pixels
 */
fun View.setMarginBot(marginBot: Int) {
    val menuLayoutParams = this.layoutParams as ViewGroup.MarginLayoutParams
    menuLayoutParams.setMargins(marginLeft, marginTop, marginRight, marginBot)
    this.layoutParams = menuLayoutParams
}

/**
 * Set view margins
 * @param marginStart - margin start in pixels
 * @param marginTop - margin top in pixels
 * @param marginEnd - margin end in pixels
 * @param marginBot - margin bot in pixels
 */
fun View.setMargins(marginStart: Int, marginTop: Int, marginEnd: Int, marginBot: Int) {
    val menuLayoutParams = this.layoutParams as ViewGroup.MarginLayoutParams
    menuLayoutParams.setMargins(marginStart, marginTop, marginEnd, marginBot)
    this.layoutParams = menuLayoutParams
}

/**
 * Set view padding start
 * @param paddingStart - padding start in pixels
 */
fun View.setPaddingStart(paddingStart: Int) {
    this.setPadding(paddingStart, paddingTop, paddingEnd, paddingBottom)
}

/**
 * Set view padding top
 * @param paddingTop - padding top in pixels
 */
fun View.setPaddingTop(paddingTop: Int) {
    this.setPadding(paddingStart, paddingTop, paddingEnd, paddingBottom)
}

/**
 * Set view padding end
 * @param paddingEnd - padding end in pixels
 */
fun View.setPaddingEnd(paddingEnd: Int) {
    this.setPadding(paddingStart, paddingTop, paddingEnd, paddingBottom)
}

/**
 * Set view padding bot
 * @param paddingBot - padding bot in pixels
 */
fun View.setPaddingBot(paddingBot: Int) {
    this.setPadding(paddingStart, paddingTop, paddingEnd, paddingBot)
}

/**
 * Start view anim
 * @param animRes - Animation resource id
 * @return [Animation]
 */
fun View.startAnim(@AnimRes animRes: Int): Animation {
    val anim = AnimationUtils.loadAnimation(this.context, animRes)
    this.startAnimation(anim)
    return anim
}

/**
 * Set view height
 * @param newHeight - new view height in pixels
 */
fun View.setHeight(newHeight: Int) {
    val params = this.layoutParams as ViewGroup.LayoutParams
    params.height = newHeight
    this.layoutParams = params
}

/**
 * Set view width
 * @param newWidth - new view width
 */
fun View.setWidth(newWidth: Int) {
    val params = this.layoutParams as ViewGroup.LayoutParams
    params.width = newWidth
    this.layoutParams = params
}

/**
 * Set view size
 * @param width - New view width or null if no changes
 * @param height - New view height or null if no changes
 */
fun View.setSize(width: Int? = null, height: Int? = null) {
    val params = this.layoutParams as ViewGroup.LayoutParams
    width?.let { params.width = it }
    height?.let { params.height = it }
    this.layoutParams = params
}

/**
 * Changes this view visibility to [View.INVISIBLE] or [View.GONE] depending on passed value.
 * @param isInvisible - True if view visibility must be changed to [View.VISIBLE]
 */
fun View.invisibleOrGone(isInvisible: Boolean) {
    if (isInvisible) setVisible(false) else setGone(false)
}