package com.example.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Addition(
    val email: String,
    val password: String,
    @SerialName("server_key") val serverKey: String,
)
