package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}

fun Date.formate(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, timeUnits: TimeUnits): Date {
    this.time += when(timeUnits) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    return this
}

fun Date.humanizeDiff(): String {

    val dateNow = Date()
    var diffDate = dateNow.time - this.time

    if (diffDate < 0) {
        diffDate *= -1
        return when(diffDate) {
            in 0 * SECOND..45 * SECOND -> "через несколько секунд"
            in 45 * SECOND..75 * SECOND -> "через минуту"
            in 75 * SECOND..45 * MINUTE -> beforeMinutes(diffDate)
            in 45 * MINUTE..75 * MINUTE -> "через час"
            in 75 * MINUTE..22 * HOUR -> beforeHours(diffDate)
            in 22 * HOUR..26 * HOUR -> "через день"
            in 26 * HOUR..360 * DAY -> beforeDays(diffDate)
            else -> "через более года"
        }
    }

    return when(diffDate) {
        in 0* SECOND..1* SECOND -> "только что"
        in 1* SECOND..45* SECOND -> "несколько секунд назад"
        in 45* SECOND..75* SECOND -> "минуту назад"
        in 75* SECOND..45 * MINUTE -> afterMinutes(diffDate)
        in 45* MINUTE..75* MINUTE -> "час назад"
        in 75* MINUTE..22* HOUR -> afterHours(diffDate)
        in 22* HOUR..26* HOUR -> "день назад"
        in 26* HOUR..360* DAY -> afterDays(diffDate)
        else -> "более года назад"
    }
}

private fun afterMinutes(time: Long): String {
    var result = ""
    result += "${time/ MINUTE} "

    result += when(time) {
        in 75* SECOND..4* MINUTE -> "минуты назад"
        in 5* MINUTE..45 * MINUTE -> "минут назад"
        else -> ""
    }
    return result
}

private fun afterHours(time: Long): String {
    var result = ""
    result += "${time/ HOUR} "

    result += when(time) {
        in 75* MINUTE..4* HOUR -> "часа назад"
        in 5* HOUR..45 * HOUR -> "часов назад"
        else -> ""
    }
    return result
}

private fun afterDays(time: Long): String {
    var result = ""
    result += "${time/ DAY} "

    result += when(time) {
        in 26* HOUR..4* DAY -> "дня назад"
        in 5* DAY..360* DAY -> "дней назад"
        else -> ""
    }
    return result
}


private fun beforeMinutes(time: Long): String {
    var result = ""
    result += "через ${time/ MINUTE} "

    result += when(time) {
        in 75* SECOND..4* MINUTE -> "минуты"
        in 5* MINUTE..45 * MINUTE -> "минут"
        else -> ""
    }
    return result
}

private fun beforeHours(time: Long): String {
    var result = ""
    result += "через ${time/ HOUR} "

    result += when(time) {
        in 75* MINUTE..119* MINUTE -> "час"
        in 119* MINUTE..4* HOUR -> "часа"
        in 5* HOUR..45 * HOUR -> "часов"
        else -> ""
    }
    return result
}

private fun beforeDays(time: Long): String {
    var result = ""
    result += "через ${time/ DAY} "

    result += when(time) {
        in 26* HOUR..47* HOUR -> "день"
        in 47* HOUR..4* DAY -> "дня"
        in 5* DAY..360* DAY -> "дней"
        else -> ""
    }
    return result
}