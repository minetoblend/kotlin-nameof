// WITH_STDLIB

package com.osucad.nameof

fun box(): String {
    class Foo {
        val foo: String = "foo"
    }

    val obj = Foo()

    val result = nameOf(obj::foo)

    return if (result == "foo") "OK" else "Fail: $result"
}