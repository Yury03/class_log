package com.example.data.database.dao.impl

import com.example.data.database.DatabaseSingleton
import com.example.data.database.dao.DatabaseDao.AttendancesDao
import com.example.data.database.models.Attendance
import com.example.data.database.models.Attendances
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class AttendancesDaoImpl : AttendancesDao {
    override suspend fun allAttendances(): List<Attendance> = DatabaseSingleton.dbQuery {
        Attendances.selectAll().map(::mapToAttendance)
    }

    private fun mapToAttendance(row: ResultRow) = Attendance(
        id = row[Attendances.id],
        comment = row[Attendances.comment],
        grade = row[Attendances.grade],
        isPresence = row[Attendances.isPresence],
        studentId = row[Attendances.studentId],
    )

    override suspend fun getAttendanceListByLessonId(id: Int): List<Attendance> {
        return transaction {
            Attendances.select {
                Attendances.lessonId eq id
            }.map { mapToAttendance(it) }
        }
    }
}