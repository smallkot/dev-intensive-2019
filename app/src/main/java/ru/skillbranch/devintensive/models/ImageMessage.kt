package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.extensions.humanizeDiff
import java.util.*

class ImageMessage(id: String,
                   from: User?,
                   chat: Chat,
                   isIncoming: Boolean = false,
                   date: Date = Date(),
                   var image: String?): BaseMessage(id, from, chat, isIncoming, date) {
    override fun formatMessage(): String {
        val statusMessage = "${if (isIncoming) "получил" else "отправил" }"
        return "${from?.firstName} $statusMessage изображение \"$image\" ${date.humanizeDiff()}"
    }
}