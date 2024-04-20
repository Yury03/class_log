package com.example.data.database.dao.impl

import com.example.data.database.dao.DatabaseSingleton
import com.example.data.database.dao.DatabaseDao
import com.example.data.database.models.Schedule
import com.example.data.database.models.Schedules
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class SchedulesDaoImpl : DatabaseDao.ScheduleDao {
    override suspend fun allSchedule(): List<Schedule> = DatabaseSingleton.dbQuery {
        Schedules.selectAll().map(::resultRowToArticle)
    }

    private fun resultRowToArticle(row: ResultRow) = Schedule(
        id = row[Schedules.id],
        weekDay = row[Schedules.weekDay]
    )

}