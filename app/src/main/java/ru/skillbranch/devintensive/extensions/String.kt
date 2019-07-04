package ru.skillbranch.devintensive.extensions

fun String.truncate(value: Int = 16): String {
    var result = this.dropLastWhile { it == ' ' }
    if (result.length < value) return result
    var value = result.length - value
    result = result.dropLast(value)
    result = result.dropLastWhile { it == ' ' }
    return  result + "..."
}