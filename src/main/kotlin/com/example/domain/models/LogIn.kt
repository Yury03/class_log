package com.example.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class LogIn(
    val email: String,
    val password: String,
)
