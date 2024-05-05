package com.example.data.database.models

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

data class Key(
    val id: Int,
    val key: String,
    val validity: Long,
)

/**
 *  Связь с [Users]
 *  */
object Keys : Table() {
    val id = integer("id").autoIncrement()
    val key = varchar("key", 32)
    val validity = long("validity")
    val userId = reference("user_id", Users.id, onDelete = ReferenceOption.CASCADE)
    override val primaryKey = PrimaryKey(id)
}
