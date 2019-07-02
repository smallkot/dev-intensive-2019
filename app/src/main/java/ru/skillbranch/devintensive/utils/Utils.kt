package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val listName = fullName?.split(" ")
        var firstName = listName?.getOrNull(0)
        var lastName = listName?.getOrNull(1)
        if (firstName == "") firstName = null
        if (lastName == "") lastName = null
        return Pair(firstName, lastName)
    }
}