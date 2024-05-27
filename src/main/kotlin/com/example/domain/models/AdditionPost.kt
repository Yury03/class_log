package com.example.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Данные для добавления нового пользователя
 * Адрес: /authentication/addUser
 * Ключ: "my_private_key"
 * */
@Serializable
data class AdditionPost(
    val email: String,
    val password: String,
    @SerialName("is_class_teacher") val isClassTeacher: Boolean,
    @SerialName("full_name") val fullName: String,
    @SerialName("server_key") val serverKey: String,
)
