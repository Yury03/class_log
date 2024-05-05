package com.example.domain.usecase

import com.example.data.database.dao.impl.UsersDaoImpl
import com.example.domain.models.Addition

class CreateNewUser(private val impl: UsersDaoImpl) {
    suspend operator fun invoke(data: Addition) = impl.addNewUser(data.email, data.fullName, data.password)
}