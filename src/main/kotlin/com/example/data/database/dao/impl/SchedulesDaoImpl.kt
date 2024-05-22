package com.example.data.database.dao.impl

import com.example.data.database.DatabaseSingleton
import com.example.data.database.dao.DatabaseDao.SchedulesDao
import com.example.data.database.models.Schedule
import com.example.data.database.models.Schedules
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class SchedulesDaoImpl : SchedulesDao {
    override suspend fun allSchedule(): List<Schedule> = DatabaseSingleton.dbQuery {
        Schedules.selectAll().map(::mapToSchedule)
    }

    override suspend fun getWeekScheduleByUserId(userId: Int) = transaction {
        Schedules.select { Schedules.userId eq userId }.map { mapToSchedule(it) }
    }

    private fun mapToSchedule(row: ResultRow) = Schedule(
        id = row[Schedules.id],
        weekDay = row[Schedules.weekDay]
    )

}