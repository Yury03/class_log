package com.example.domain.usecase


import com.example.data.database.dao.DatabaseDao.UsersDao
import com.example.domain.models.AdditionPost

class CreateNewUser(private val usersImpl: UsersDao) {
    suspend operator fun invoke(data: AdditionPost) =
        usersImpl.addNewUser(data.email, data.fullName, data.password, data.isClassTeacher)
}