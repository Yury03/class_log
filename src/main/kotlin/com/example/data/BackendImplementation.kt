package com.example.data

import com.example.data.database.dao.DatabaseDao.*
import com.example.data.database.models.Lesson
import com.example.domain.models.GetSchedulePost
import com.example.domain.models.LogInPost

// todo неправильные зависимости, добавить интерфейс [?]
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

