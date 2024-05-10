package com.example.data

import com.example.data.database.dao.DatabaseDao.KeysDao
import com.example.data.database.dao.DatabaseDao.UsersDao
import com.example.data.database.dao.impl.SchedulesDaoImpl
import com.example.data.database.models.Lesson
import com.example.domain.models.GetSchedule


suspend fun authentication(usersImpl: UsersDao, keysImpl: KeysDao, email: String, inputPassword: String): String? {
    return usersImpl.getUserIdWithAuthentication(email = email, inputPassword = inputPassword)?.let { userId ->
        keysImpl.getKeyByUserId(userId = userId)?.key
    }
}

suspend fun getSchedule(impl: SchedulesDaoImpl, data: GetSchedule): List<Lesson> {
//    impl.getWeekScheduleByUserId()
    TODO()
}
