package com.example.data.database.dao.impl

import com.example.data.database.dao.DatabaseDao
import com.example.data.database.dao.DatabaseSingleton.dbQuery
import com.example.data.database.models.User
import com.example.data.database.models.Users
import com.example.data.database.utils.hashPassword
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class UsersDaoImpl : DatabaseDao.UsersDao {
    private fun resultRowToArticle(row: ResultRow) = User(
        id = row[Users.id],
        email = row[Users.email],
        fullName = row[Users.fullName],
        passwordHash = row[Users.passwordHash],
        isClassTeacher = row[Users.isClassTeacher],
    )

    override suspend fun allUsers(): List<User> = dbQuery {
        Users.selectAll().map(::resultRowToArticle)
    }

    override suspend fun editPassword(id: Int, newPassword: String): Boolean = dbQuery {
        Users.update({ Users.id eq id }) {
            it[passwordHash] = hashPassword(newPassword)
        } > 0
    }

    override suspend fun addNewUser(email: String, fullName: String, password: String) = dbQuery {
        val insertStatement = Users.insert {
            it[Users.email] = email
            it[Users.fullName] = fullName
            it[passwordHash] = hashPassword(password)
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToArticle)
    }

}