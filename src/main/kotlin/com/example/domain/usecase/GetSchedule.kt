package com.example.domain.usecase

import com.example.data.database.dao.DatabaseDao.*
import com.example.data.getSchedule
import com.example.domain.models.GetSchedulePost


class GetSchedule(
    private val schedulesImpl: SchedulesDao,
    private val lessonsDao: LessonsDao,
    private val keysImpl: KeysDao,
) {
    suspend operator fun invoke(data: GetSchedulePost) = getSchedule(
        schedulesImpl = schedulesImpl,
        lessonsImpl = lessonsDao,
        keysImpl = keysImpl,
        data = data
    )
}