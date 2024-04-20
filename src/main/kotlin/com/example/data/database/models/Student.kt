package com.example.data.database.models

import org.jetbrains.exposed.sql.Table

data class Student(
    val id: Int,
    val fullName: String,
    val parentPhoneNumber: Long,
    val phoneNumber: Long,
)

object Students : Table() {
    val id = integer("id").autoIncrement()
    val fullName = varchar("full_name", 64)
    val parentPhoneNumber = long("parent_phone_number")
    val phoneNumber = long("phone_number")
    override val primaryKey = PrimaryKey(Users.id)
}