package com.example.data

import com.example.data.database.dao.impl.KeysDaoImpl
import com.example.data.database.dao.impl.UsersDaoImpl
import com.example.data.database.models.Users
import com.example.data.database.utils.hashPassword
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction


suspend fun authentication(impl: UsersDaoImpl, email: String, inputPassword: String): String? {
    return transaction {
        Users.select { Users.email eq email } // находим пользователя с данным email
            .singleOrNull()
            ?.let { user ->
                if (user[Users.passwordHash] == hashPassword(inputPassword)) { // сравниваем хеш введенного пароля
                    runBlocking {// todo этот код уже в suspend?
                        KeysDaoImpl().addNewKey(userId = user[Users.id])?.key
                    }
                } else {
                    null
                }
            }
    }
}
