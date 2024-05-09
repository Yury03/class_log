package com.example.data.database.utils

import java.security.MessageDigest
import java.util.*

internal fun hashPassword(password: String): String {
    val digest = MessageDigest.getInstance("SHA-256")
    val hash = digest.digest(password.toByteArray())
    return Base64.getEncoder().encodeToString(hash)
}