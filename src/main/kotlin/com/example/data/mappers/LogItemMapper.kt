package com.example.data.mappers

import com.example.data.database.models.Attendance
import com.example.data.database.models.Student
import com.example.domain.models.LogItemGet

object LogItemMapper {
    fun mapDataToLogItem(
        attendance: Attendance,
        student: Student,
    ) = LogItemGet(
        id = attendance.id,
        name = student.fullName,
        score = attendance.grade,
        comment = attendance.comment,
        presence = attendance.isPresence,
    )
}