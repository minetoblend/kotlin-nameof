// WITH_STDLIB

package com.osucad.nameof

fun nameOf(value: Any?): String = ""

fun box(): String {
    val foo = "bar"

    val result = nameOf(foo)

    return if (result == "foo") "OK" else "Fail: $result"
}
