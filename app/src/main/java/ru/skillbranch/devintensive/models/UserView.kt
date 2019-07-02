package ru.skillbranch.devintensive.models

class UserView (val id: String,
                val fullName: String,
                val nickName: String,
                val avatarName: String? = null,
                val status: String? = "offline",
                val initials: String?
) {
    fun printMe() {
        println("""
        $id
        $fullName
        $nickName
        $avatarName
        $status
        $initials
        """)
    }
}