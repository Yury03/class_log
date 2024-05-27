package com.example.server.handlers

import com.example.data.database.dao.impl.KeysDaoImpl
import com.example.data.database.dao.impl.LessonsDaoImpl
import com.example.data.database.dao.impl.SchedulesDaoImpl
import com.example.domain.models.GetSchedulePost
import com.example.domain.usecase.GetSchedule
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.util.pipeline.*

private val lessonsImpl = LessonsDaoImpl()
private val keysImpl = KeysDaoImpl()
private val schedulesImpl = SchedulesDaoImpl()

private val getScheduleUseCase = GetSchedule(schedulesImpl, lessonsImpl, keysImpl)

internal suspend fun PipelineContext<Unit, ApplicationCall>.schedulesHandler() {
    call.receive<GetSchedulePost>().also { data ->
        getScheduleUseCase(data)
    }
}