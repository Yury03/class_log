package com.example.domain.models

import kotlinx.serialization.Serializable

/**
 * UI Модель
 * */
@Serializable
data class LessonGet(
    val id: Int,
    val audience: String, // 101
    val startTime: String, // 9:00
    val endTime: String, // 10:00
    val theme: String, // Контрольная работа
    val subject: String, // Химия
    val schoolClass: String, // 9Б
)
