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

    fun transliteration(payload: String, divider: String = " "): String {
        if (payload.isEmpty()) return ""

        var result = ""
        for (letter in payload) {
            val isUpperCase = letter.isUpperCase()
            var letterTemp = ""
            letterTemp += when(letter.toLowerCase()) {
                'а' -> "a"
                'б' -> "b"
                'в' -> "v"
                'г' -> "g"
                'д' -> "d"
                'е' -> "e"
                'ё' -> "e"
                'ж' -> "zh"
                'з' -> "z"
                'и' -> "i"
                'й' -> "i"
                'к' -> "k"
                'л' -> "l"
                'м' -> "m"
                'н' -> "n"
                'о' -> "o"
                'п' -> "p"
                'р' -> "r"
                'с' -> "s"
                'т' -> "t"
                'у' -> "u"
                'ф' -> "f"
                'х' -> "h"
                'ц' -> "c"
                'ч' -> "ch"
                'ш' -> "sh"
                'щ' -> "sh'"
                'ъ' -> ""
                'ы' -> "i"
                'ь' -> ""
                'э' -> "e"
                'ю' -> "yu"
                'я' -> "ya"
                ' ' -> divider
                else -> letter
            }
            result += if(isUpperCase) letterTemp.toUpperCase() else letterTemp
        }
        return result
    }
}