package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User(val id : String,
                var firstName : String?,
                var lastName : String?,
                var avatar : String?,
                var rating : Int = 0,
                var respect : Int = 0,
                var lastVisit : Date? = Date(),
                var isOnline : Boolean = false) {

    companion object Factory {
        private var indexId = 0
        fun makeUser(fullName: String?): User {
            indexId++
            var (firstName, lastName) = Utils.parseFullName(fullName)
            return User("$indexId", firstName, lastName, null)
        }
    }
}