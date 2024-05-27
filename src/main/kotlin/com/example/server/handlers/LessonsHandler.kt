package com.example.server.handlers

import com.example.data.database.dao.impl.*
import com.example.domain.models.GetSchedulePost
import com.example.domain.usecase.GetLogByLesson
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.util.pipeline.*

private val lessonsImpl = LessonsDaoImpl()
private val keysImpl = KeysDaoImpl()
private val schedulesImpl = SchedulesDaoImpl()
private val attendanceImpl = AttendancesDaoImpl()
private val studentsImpl = StudentsDaoImpl()

private val getLogByLessonUseCase = GetLogByLesson(schedulesImpl, lessonsImpl, keysImpl, studentsImpl, attendanceImpl)

internal suspend fun PipelineContext<Unit, ApplicationCall>.logsHandler(id: Int) {
    call.receive<GetSchedulePost>().also { data ->
        getLogByLessonUseCase(data = data, id = id)
    }
}