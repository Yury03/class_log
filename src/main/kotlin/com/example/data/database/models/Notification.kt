package com.example.data.database.models

import org.jetbrains.exposed.sql.Table

data class Notification(
    val id: Int,
    val title: String,
    val body: String,
    val time: Long,
    val wasRead: Boolean,
)

data object Notifications : Table() {
    val id = integer("id").autoIncrement()
    val title = varchar("title", 32)
    val body = varchar("body", 128)
    val time = long("time")
    val wasRead = bool("was_read")
    val userId = integer("user_id")
    override val primaryKey = PrimaryKey(Lessons.id)
}
