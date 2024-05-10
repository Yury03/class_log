package com.example.domain.usecase

import com.example.data.database.dao.impl.SchedulesDaoImpl
import com.example.data.getSchedule


class GetSchedule(private val impl: SchedulesDaoImpl) {
    suspend operator fun invoke(key: String) = getSchedule(impl = impl, key = key)
}