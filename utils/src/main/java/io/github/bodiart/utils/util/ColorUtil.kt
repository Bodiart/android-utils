package io.github.bodiart.utils.util

import android.graphics.Color
import androidx.annotation.ColorInt

/**
 * Pattern of color hex.
 */
private const val COLOR_HEX_PATTERN = "#%06X"

/**
 * Returns readable color (white or black) for specified background color
 * @param backgroundHexColorStr - bg color in hex format
 */
fun getReadableColorForBg(backgroundHexColorStr: String): Int{
    return try {
        val luminance = calculateLuminance(hexToRBG(backgroundHexColorStr))
        if (luminance < 140) Color.WHITE else Color.BLACK
    } catch (ex: Exception) {
        Color.BLACK
    }
}

/**
 * Convert color int to hex string
 * @return color hex
 */
fun colorToHex(@ColorInt color: Int): String = String.format(COLOR_HEX_PATTERN, 0xFFFFFF and color)

/**
 * Calculating luminance.
 * Need for [getReadableColorForBg]
 */
private fun calculateLuminance(rgb: ArrayList<Int>): Double {
    return (0.2126 * rgb[0] + 0.7152 * rgb[1] + 0.0722 * rgb[2])
}

/**
 * Converts hex to rgb arr
 * @return color rgb arr
 */
private fun hexToRBG(colorStr: String): ArrayList<Int> {
    val rbg = ArrayList<Int>()
    rbg.add(Integer.valueOf(colorStr.substring(1, 3), 16))
    rbg.add(Integer.valueOf(colorStr.substring(3, 5), 16))
    rbg.add(Integer.valueOf(colorStr.substring(5, 7), 16))
    return rbg
}