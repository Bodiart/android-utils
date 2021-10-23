package io.github.bodiart.utils.extensions

import androidx.viewpager.widget.ViewPager

/**
 * Add listener to [ViewPager]
 * @param onPageScrollStateChanged - On page scroll state changed callback or null
 * @param onPageScrolled - On page scrolled callback or null
 * @param onPageSelected - On page selected callback or null
 */
fun ViewPager.addListener(
    onPageScrollStateChanged: ((state: Int) -> Unit)?,
    onPageScrolled: ((position: Int, positionOffset: Float, positionOffsetPixels: Int) -> Unit)?,
    onPageSelected: ((position: Int) -> Unit)?
) {
    this.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {
            onPageScrollStateChanged?.invoke(state)
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            onPageScrolled?.invoke(position, positionOffset, positionOffsetPixels)
        }

        override fun onPageSelected(position: Int) {
            onPageSelected?.invoke(position)
        }
    })
}