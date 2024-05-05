package com.example.domain.usecase

import com.example.data.authentication
import com.example.data.database.dao.impl.UsersDaoImpl
import com.example.domain.models.LogIn

class Authentication(private val impl: UsersDaoImpl) {
    suspend operator fun invoke(logIn: LogIn) = authentication(logIn.email, logIn.password)
}