package com.example.domain.usecase

import com.example.data.authentication
import com.example.data.database.dao.DatabaseDao.KeysDao
import com.example.data.database.dao.DatabaseDao.UsersDao
import com.example.domain.models.LogInPost

class Authentication(
    private val usersImpl: UsersDao,
    private val keysImpl: KeysDao,
) {
    suspend operator fun invoke(data: LogInPost) =
        authentication(usersImpl = usersImpl, keysImpl = keysImpl, data = data)
}