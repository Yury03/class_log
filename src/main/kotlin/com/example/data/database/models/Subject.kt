package com.example.data.database.models

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

data class Subject(
    val id: Int,
    val title: String,
)
/**
 *  Связь с [SchoolClasses]
 *  */
object Subjects : Table() {
    val id = integer("id").autoIncrement()
    val title = varchar("title", 64)
    val classId = reference("class_id", SchoolClasses.id)
    override val primaryKey = PrimaryKey(id)
}