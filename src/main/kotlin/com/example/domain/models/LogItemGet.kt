package com.example.domain.models

/**
 * UI Модель
 * адрес: /schedule/id
 * */
data class LogItemGet(
    val id: Int,
    val name: String,
    val score: Int,
    val comment: String,
    val presence: Boolean,
)