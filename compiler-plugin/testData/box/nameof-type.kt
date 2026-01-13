// WITH_STDLIB

package com.osucad.nameof

inline fun <reified T: Any> nameOf(): String = ""

fun box(): String {
    class Foo {
        val foo: String = "foo"
    }

    val result = nameOf<Foo>()

    return if (result == "Foo") "OK" else "Fail: $result"
}
