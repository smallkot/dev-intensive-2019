package ru.skillbranch.devintensive.extensions

fun String.truncate(value: Int = 16): String {
    var result = this.dropLastWhile { it == ' ' }
    result = result.dropWhile { it == ' ' }
    if (result.length <= value+1) return result
    //var value = result.length - value
    result = result.removeRange(value+1, result.length)//result.dropLast(value)
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
    var isAllRemove = false
    var textTemp = this
    var startIndex: Int

    while (!isAllRemove) {
        when {
            textTemp.indexOf("&#39;") != -1 -> {
                startIndex = textTemp.indexOf("&#39;")
                textTemp = textTemp.removeRange(startIndex, startIndex+5)
            }
            textTemp.indexOf("&quot;") != -1 -> {
                startIndex = textTemp.indexOf("&quot;")
                textTemp = textTemp.removeRange(startIndex, startIndex+6)
            }
            textTemp.indexOf("&gt;") != -1 -> {
                startIndex = textTemp.indexOf("&gt;")
                textTemp = textTemp.removeRange(startIndex, startIndex+4)
            }
            textTemp.indexOf("&lt;") != -1 -> {
                startIndex = textTemp.indexOf("&lt;")
                textTemp = textTemp.removeRange(startIndex, startIndex+4)
            }
            textTemp.indexOf("&amp;") != -1 -> {
                startIndex = textTemp.indexOf("&amp;")
                textTemp = textTemp.removeRange(startIndex, startIndex+5)
            }
            else -> isAllRemove = true
        }
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
            index = startIndex-1
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
            index = indexSpace-1
            indexSpace = -1
        } else {
            indexSpace = -1
        }

        if (index+1 == textTemp.length && indexSpace != -1) {
            textTemp = textTemp.removeRange(indexSpace, index)
            index = indexSpace
            indexSpace = -1
        }
        index++
    }
    return textTemp
}