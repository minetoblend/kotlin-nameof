// WITH_STDLIB

package com.osucad.nameof

fun nameOf(value: Any?): String = ""

fun box(): String {
    val `Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua` = "bar"

    val result = nameOf(`Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua`)

    return if (result == "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua") "OK" else "Fail: $result"
}
