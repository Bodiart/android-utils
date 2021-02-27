package com.bodiart.utils.extensions

import androidx.lifecycle.MutableLiveData

/**
 * Helper function to modify [MutableLiveData] value if it exists.
 *
 * @param T Type of [MutableLiveData] value
 *
 * @param block Function that transforms current [MutableLiveData] value to new value
 */
fun <T> MutableLiveData<T>.change(block: T.() -> T) {
    value?.let { value = block(it) }
}