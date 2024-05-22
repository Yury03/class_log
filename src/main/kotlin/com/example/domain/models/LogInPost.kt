package com.example.domain.models

import kotlinx.serialization.Serializable

/**
 * Данные для авторизации
 * Адрес: /authentication
 * Ответ: ключ авторизации (String)
 * */
@Serializable
data class LogInPost(
    val email: String,
    val password: String,
)
