package com.example.data.database.models

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

data class Subject(
    val id: Int,
    val title: String,
)

object Subjects : Table() {
    val id = Students.integer("id").autoIncrement()
    val title = varchar("title", 64)
    val classId = reference("class_id", SchoolClasses.id, onDelete = ReferenceOption.CASCADE)
    override val primaryKey = PrimaryKey(Users.id)
}