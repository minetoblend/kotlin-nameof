# kotlin-nameof

A kotlin compiler plugin that provides a nameOf function which is replaced by the name of the provided value at compile time.

### Examples

```kt

class MyClass {
    val bar = "baz"
}

val foo = "bar"

nameOf(foo) // "foo"

nameOf(MyClass().bar) // "bar"

nameOf(MyClass::bar) // "bar

nameOf<MyClass>() // "MyClass"
```