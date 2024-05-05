package com.example.data.database.models

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

data class Lesson(
    val id: Int,
    val audience: String,
    val startTime: Long,
    val endTime: Long,
    val theme: String,
)

/**
 *  Связь с [SchoolClasses], [Schedules], [Subjects].
 *  */
object Lessons : Table() {
    val id = integer("id").autoIncrement()
    val audience = varchar("audience", 64)
    val startTime = long("start_time")
    val endTime = long("end_time")
    val theme = varchar("theme", 256)
    val classId = reference("class_id", SchoolClasses.id, onDelete = ReferenceOption.CASCADE)
    val scheduleId = reference("schedule_id", Schedules.id, onDelete = ReferenceOption.CASCADE)
    val subjectId = reference("subject_id", Subjects.id, onDelete = ReferenceOption.CASCADE)
    override val primaryKey = PrimaryKey(id)
}