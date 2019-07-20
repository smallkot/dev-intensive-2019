package ru.skillbranch.devintensive.models

import java.util.*

abstract class BaseMessage(val id: String,
                           val from: User?,
                           val chat: Chat,
                           val isIncoming: Boolean = false,
                           val date: Date = Date()) {
    abstract fun formatMessage(): String

    companion object AbstractFactory {
        var indexId = -1
        fun makeMessage(from: User?, chat: Chat, date: Date = Date(), type: String = "text", payload: Any?, isIncoming: Boolean = false): BaseMessage {
            indexId++
            return when (type) {
                "image" -> ImageMessage(id = "$indexId", from = from, chat = chat, date = date, image = payload as String, isIncoming = isIncoming)
                else -> TextMessage(id = "$indexId", from = from, chat = chat, date = date, text = payload as String, isIncoming = isIncoming)
            }
        }
    }
}