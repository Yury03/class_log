package com.example.data

import com.example.data.database.dao.DatabaseDao.*
import com.example.data.database.models.Lesson
import com.example.data.mappers.LogItemMapper
import com.example.domain.models.GetSchedulePost
import com.example.domain.models.LogInPost
import com.example.domain.models.LogItemGet

suspend fun authentication(
    usersImpl: UsersDao,
    keysImpl: KeysDao,
    data: LogInPost,
): String? = usersImpl.getUserIdWithAuthentication(email = data.email, inputPassword = data.password)?.let { userId ->
    keysImpl.getKeyByUserId(userId = userId)?.key
}


suspend fun getSchedule(
    schedulesImpl: SchedulesDao,
    keysImpl: KeysDao,
    lessonsImpl: LessonsDao,
    data: GetSchedulePost
): List<Lesson>? =
    keysImpl.getUserIdByKeyString(key = data.key)?.let { userId ->
        schedulesImpl.getWeekScheduleByUserId(userId = userId).flatMap { schedule ->
            lessonsImpl.getLessonsByScheduleId(scheduleId = schedule.id)
        }
    }

suspend fun getLogByLesson(
    schedulesImpl: SchedulesDao,
    keysImpl: KeysDao,
    lessonsImpl: LessonsDao,
    attendancesImpl: AttendancesDao,
    studentsImpl: StudentsDao,
    data: GetSchedulePost,
    lessonId: Int
): List<LogItemGet> {
    val lessonDbId = getSchedule(
        schedulesImpl = schedulesImpl,
        keysImpl = keysImpl,
        lessonsImpl = lessonsImpl,
        data = data
    )!![lessonId].id
    val attendanceList = attendancesImpl.getAttendanceListByLessonId(id = lessonDbId)
    return attendanceList.map { attendance ->
        val student = studentsImpl.getStudentById(attendance.studentId)
        LogItemMapper.mapDataToLogItem(
            attendance = attendance,
            student = student
        )
    }
}