package com.example.data.database.dao.impl

import com.example.data.database.dao.DatabaseSingleton
import com.example.data.database.dao.DatabaseDao
import com.example.data.database.models.Attendance
import com.example.data.database.models.Attendances
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class AttendancesDaoImpl : DatabaseDao.AttendancesDao {
    override suspend fun allAttendances(): List<Attendance> = DatabaseSingleton.dbQuery {
        Attendances.selectAll().map(::resultRowToArticle)
    }

    private fun resultRowToArticle(row: ResultRow) = Attendance(
        id = row[Attendances.id],
        comment = row[Attendances.comment],
        grade = row[Attendances.grade],
        isPresence = row[Attendances.isPresence]
    )
}