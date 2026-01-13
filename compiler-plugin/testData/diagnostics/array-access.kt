// RUN_PIPELINE_TILL: FRONTEND

package foo

import com.osucad.nameof.nameOf

fun test() {
    val values = listOf(0, 1, 2, 3)

    nameOf(<!ARRAY_ACCESS!>values[3]<!>)
}
