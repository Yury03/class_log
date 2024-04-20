package com.example.data.database.models

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

data class Attendance(
    val id: Int,
    val comment: String,
    val grade: Int,
    val isPresence: Boolean,
)

object Attendances : Table() {
    val id = integer("id").autoIncrement()
    val comment = varchar("comment", 128)
    val grade = integer("grade")
    val isPresence = bool("is_presence")
    val studentId = reference("student_id", Students.id, onDelete = ReferenceOption.CASCADE)
    val scheduleId = reference("schedule_id", Schedules.id, onDelete = ReferenceOption.CASCADE)
    val subjectId = reference("subject_id", Subjects.id, onDelete = ReferenceOption.CASCADE)
    override val primaryKey = PrimaryKey(Users.id)
}
