package com.example.domain.usecase

import com.example.data.database.dao.DatabaseDao.*
import com.example.data.getLogByLesson
import com.example.domain.models.GetSchedulePost

class GetLogByLesson(
    private val schedulesImpl: SchedulesDao,
    private val lessonsImpl: LessonsDao,
    private val keysImpl: KeysDao,
    private val studentsImpl: StudentsDao,
    private val attendancesImpl: AttendancesDao,
) {
    suspend operator fun invoke(data: GetSchedulePost, id: Int) = getLogByLesson(
        schedulesImpl = schedulesImpl,
        lessonsImpl = lessonsImpl,
        keysImpl = keysImpl,
        attendancesImpl = attendancesImpl,
        studentsImpl = studentsImpl,
        data = data,
        lessonId = id,
    )
}