package com.niranjan.khatri.androidcomposetutorial.advanced


/**
 * ProGuard
 * -keep class com.example.MyClass { *; }
 * -keepclassmembers class com.example.MyClass { public void myMethod(); }
 *
 *
 * -dontwarn <class_or_package_specification>
 *    -dontwarn com.example.library.**
 *
 *
 * -keep class com.example.MyClass {
 *     public void myMethod();
 * }
 * -keepattributes Signature
 *
 * -keepnames class com.example.MyClass {
 *     public *;
 * }
 *
 */
class MyClass {
    fun myMethod() {
        // ...
    }
}

fun CallingFun() {
// Reflection usage
    val method = MyClass::class.java.getMethod("myMethod")
    method.invoke(MyClass())
}