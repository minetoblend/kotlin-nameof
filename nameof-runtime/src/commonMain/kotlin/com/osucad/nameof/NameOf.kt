package com.osucad.nameof

public fun nameOf(value: Any?): String = throw UnsupportedOperationException("nameof plugin is not applied")

public inline fun <reified T: Any?> nameOf(): String = throw UnsupportedOperationException("nameof plugin is not applied")
