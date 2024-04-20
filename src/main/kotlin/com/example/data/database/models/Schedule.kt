package com.example.data.database.models

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

data class Schedule(
    val id: Int,
    val weekDay: Int, //пн - 1, вс - 7
)

object Schedules : Table() {
    val id = integer("id").autoIncrement()
    val weekDay = integer("week_day")
    val userId = reference("user_id", Users.id, onDelete = ReferenceOption.CASCADE)
    override val primaryKey = PrimaryKey(Users.id)
}
