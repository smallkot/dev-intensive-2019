package ru.skillbranch.devintensive.extensions

fun String.truncate(value: Int = 16): String {
    var result = this.dropLastWhile { it == ' ' }
    if (result.length < value) return result
    var value = result.length - value
    result = result.dropLast(value)
    result = result.dropLastWhile { it == ' ' }
    return  result + "..."
}

fun String.stripHtml(): String {
    var textHtml = this
    textHtml = textHtml.removeHtmlTag()
    textHtml = textHtml.removeHtmlEscape()
    return textHtml.removeSpace()
}

fun String.removeHtmlEscape(): String {
    var index = 0
    var textTemp = this

    var removeChar = {
        textTemp = textTemp.removeRange(index, index+1)
        index--
    }
    while (index <  textTemp.length) {
        when(textTemp[index]) {
            '&' -> removeChar()
            '<' -> removeChar()
            '>' -> removeChar()
            '\'' -> removeChar()
            '\"' -> removeChar()
        }
        index++
    }
    return textTemp
}

fun String.removeHtmlTag(): String {
    var startIndex = -1
    var index = 0
    var textTemp = this
    while(index < textTemp.length) {
        if (textTemp[index] == '<' && startIndex == -1) {
            startIndex = index
        } else if (textTemp[index] == '>' && startIndex != -1) {
            textTemp = textTemp.removeRange(startIndex, index+1)
            index -= index - startIndex
            startIndex = -1
        }
        index++
    }
    return textTemp
}

fun String.removeSpace(): String {
    var indexSpace = -1
    var index = 0
    var textTemp = this
    while(index < textTemp.length) {
        if (textTemp[index] == ' ') {
            if (indexSpace == -1) indexSpace = index
        } else if (indexSpace != -1 && index - indexSpace > 1) {
            textTemp = textTemp.removeRange(indexSpace, index-1)
            index -= index - indexSpace - 1
            indexSpace = -1
        } else {
            indexSpace = -1
        }
        index++
    }
    textTemp = textTemp.dropLastWhile { it == ' ' }
    return textTemp
}