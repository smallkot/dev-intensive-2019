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

    fun toInitials(firstName: String?, lastName: String?): String? {
        var firstNameLetter: Char? = null
        var lastNameLetter: Char? = null

        if (firstName != null && firstName.isNotEmpty()) firstNameLetter = firstName[0].toUpperCase()
        if (lastName != null && lastName.isNotEmpty()) lastNameLetter = lastName[0].toUpperCase()

        var printStr = ""
        if (firstNameLetter != null && firstNameLetter != ' ') printStr += firstNameLetter
        if (lastNameLetter != null && lastNameLetter != ' ') printStr += lastNameLetter

        return if (printStr.isEmpty()) null else printStr
    }
}