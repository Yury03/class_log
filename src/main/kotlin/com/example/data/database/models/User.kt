package com.example.data.database.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class User(
    val id: Int,
    val email: String,
    val isClassTeacher: Boolean,
    @SerialName("full_name")
    val fullName: String,
    @SerialName("password_hash")
    val passwordHash: String,
)
/**
 *  Не имеет связей
 *  */
object Users : Table() {
    val id = integer("id").autoIncrement()
    val email = varchar("email", 128)
    val isClassTeacher = bool("isClassTeacher")
    val fullName = varchar("full_name", 128)
    val passwordHash = varchar("password_hash", 128)
    override val primaryKey = PrimaryKey(id)
}