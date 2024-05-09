package com.example.data.database.models

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

data class Notification(
    val id: Int,
    val title: String,
    val body: String,
    val time: Long,
    val wasRead: Boolean,
)

/**
 *  Связь с [Users]
 *  */
data object Notifications : Table() {
    val id = integer("id").autoIncrement()
    val title = varchar("title", 32)
    val body = varchar("body", 128)
    val time = long("time")
    val wasRead = bool("was_read")
    val userId = reference("user_id", Users.id, onDelete = ReferenceOption.CASCADE)
    override val primaryKey = PrimaryKey(id)
}
