package com.example.domain.models

import kotlinx.serialization.Serializable

/**
 * Данные для получения расписания
 * Адрес: /schedule
 * Ответ: List<Lesson>
 * */
@Serializable
data class GetSchedule(
    val key: String,
    val date: Long,
)
