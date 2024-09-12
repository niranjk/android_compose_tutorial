package com.niranjan.khatri.androidcomposetutorial.kotlinconcepts

import com.niranjan.khatri.androidcomposetutorial.mvi.model.FactState
import java.io.File
import java.io.IOException

// #1. Extensible Data Arguments (data objects)
// Before (lots of parameters)
fun myFunction(param1: Int = 0, param2: String = "", param3: Boolean = false) {
    // ...
}

// After (using data object)
data object MyFunctionOptions {
    var param1: Int = 0
    var param2: String = ""
    var param3: Boolean = false
}

fun myFunction(options: MyFunctionOptions = MyFunctionOptions) {
    // Access options.param1, options.param2, etc.
}

fun MyCallerFunction(){
    // Calling the function
    myFunction(param1 = 10, param3 = true) // No need to create MyFunctionOptions object
}


// #2 Guarded Conditions (if inside when) : introduced in Kotlin 2.1
/*
fun guardedConditions() {
    val numbers = listOf(2, 5, 8, 12, 15)

    for (x in numbers) {
        when (x) {
            in 1..10 if x % 2 == 0 -> println("$x is an even number between 1 and 10")
            else -> println("$x is not an even number between 1 and 10")
        }
    }
}

 */

// #3. Context-Sensitive Resolution for Sealed Classes and Enums
sealed class KResult {
    object KSuccess : KResult()
    data class KError(val message: String) : KResult()
}

fun handleResult(result: KResult) {
    when (result) {
        is KResult.KSuccess -> println("Success!")
        is KResult.KError -> println("Error: ${result.message}")
    }
}

fun ContextSensitiveMain() {
    val successResult = KResult.KSuccess
    val errorResult = KResult.KError("Something went wrong")

    handleResult(successResult)
    handleResult(errorResult)
}

// #4. Destructing declarations :

data class User(val name: String, val age: Int)

fun DestructingDeclarations() {
    val user = User("Alice", 30)

    val (n, a) = user
    println("Name: $n, Age: $a")
}

// #5. Explicit Backing Field
class Person {
    var name: String = ""
        set(value) {
            if (value.isNotBlank()) {
                field = value // Use 'field' to access the backing field
            } else {
                println("Error: Name cannot be blank.")
            }
        }

    var age: Int = 0
        private set // Private setter, modifiable only within the class

    fun haveBirthday() {
        age += 1 // Use 'field' to modify the backing field of 'age'
    }
}

fun ExplicitBackingField() {
    val person = Person()
    person.name = "Alice"
    println("Name: ${person.name}")

    person.name = "" // Try to set an invalid name
    println("Name: ${person.name}") // Name remains unchanged

    println("Age: ${person.age}")
    person.haveBirthday()
    println("Age: ${person.age}")
}

// #6. String Templates in Triple-Quoted Strings
fun main() {
    val name = "World"
    val age = 30

    val message = """
        Hello, $${name}! 
        You are $${age} years old.
    """.trimIndent()

    println(message)
}

// #7. Union Types for Errors
// Traditional Approach
fun readFileTraditional(path: String): Any {
    return try {
        val file = File(path)
        if (!file.exists()) {
            throw IllegalArgumentException("File does not exist: $path")
        }
        file.readText() // Simulate reading file content
    } catch (e: IOException) {
        e // Return the exception as a result
    }
}

fun readingFileTraditionalApproach() {
    val result = readFileTraditional("file.txt")
    if (result is IOException) {
        println("Error reading file: ${result.message}")
    } else if (result is IllegalArgumentException) {
        println("Invalid argument: ${result.message}")
    } else if (result is String) {
        println("File content: $result")
    }
}
//
