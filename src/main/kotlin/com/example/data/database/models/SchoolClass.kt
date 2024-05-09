package com.example.data.database.models

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

data class SchoolClass(
    val id: Int,
    val className: String,
)

/**
 *  Связь с [Users]
 *  */
object SchoolClasses : Table() {
    val id = integer("id").autoIncrement()
    val className = varchar("class_name", 64)
    val classTeacherId = reference("class_teacher_id", Users.id, onDelete = ReferenceOption.CASCADE)
    override val primaryKey = PrimaryKey(id)
}