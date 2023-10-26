package utils

import models.OutputMessage

fun containNumbers(value: String): Boolean {
    val regex = Regex("\\d")
    return value.contains(regex)
}

fun containSpecialChars(value: String): Boolean {
    val regex = Regex("\\W")
    return value.contains(regex)
}

fun isOnRange(value: String): Boolean {
    return value.length == 5
}

fun isValidInput(value: String, output: OutputMessage): Boolean {
    if (containNumbers(value)) {
        println(output.onInvalidInputNumbers)
        return false
    }

    if (containSpecialChars(value)) {
        println(output.onInvalidInputSpecialChars)
        return false
    }

    if (!isOnRange(value)) {
        println(output.onInvalidInputOutOfRange)
        return false
    }

    return true
}