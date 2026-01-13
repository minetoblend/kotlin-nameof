// RUN_PIPELINE_TILL: FRONTEND

package foo

import com.osucad.nameof.nameOf

fun test() {
    nameOf(<!CONSTANT!>0<!>)
}

annotation class Foo

@Foo
fun foo() = "bar"