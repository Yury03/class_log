package com.example.data.database.dao.impl

import com.example.data.database.DatabaseSingleton.dbQuery
import com.example.data.database.dao.DatabaseDao.LessonsDao
import com.example.data.database.models.Lesson
import com.example.data.database.models.Lessons
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class LessonsDaoImpl : LessonsDao {
    private fun mapToLessons(row: ResultRow) = Lesson(
        id = row[Lessons.id],
        audience = row[Lessons.audience],
        startTime = row[Lessons.startTime],
        endTime = row[Lessons.endTime],
        theme = row[Lessons.theme],
    )

    override suspend fun allLessons(): List<Lesson> = dbQuery {
        Lessons.selectAll().map(::mapToLessons)
    }

    override suspend fun getLessonsByScheduleId(scheduleId: Int): List<Lesson> {
        return transaction {
            Lessons.select {
                Lessons.scheduleId eq scheduleId
            }.orderBy(Lessons.startTime)
        }.map { mapToLessons(it) }
    }

}