package com.example.domain.models

import kotlinx.serialization.Serializable

/**
 * Данные для получения расписания
 * Адрес: /schedule
 * Ответ: List<Lesson>
 * */
@Serializable
data class GetSchedulePost(
    val key: String,
    val date: Long,
)
