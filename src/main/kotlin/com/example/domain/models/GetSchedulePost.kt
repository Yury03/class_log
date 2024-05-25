package com.example.domain.models

import kotlinx.serialization.Serializable

/**
 * Данные для получения расписания
 * Адреса: /schedule, /schedule/id
 * Ответ: List<Lesson>, List<>
 * */
@Serializable
data class GetSchedulePost(
    val key: String,
    val date: String, //dd-mm-yyyy
)
