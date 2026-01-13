// WITH_STDLIB

package com.osucad.nameof

fun nameOf(value: Any?): String = ""

fun box(): String {
    class Foo {
        val foo: String = "bar"
    }

    val result = nameOf(Foo::foo)

    return if (result == "foo") "OK" else "Fail: $result"
}
