package com.example.data.database.dao.impl

import com.example.data.database.dao.DatabaseSingleton
import com.example.data.database.dao.DatabaseDao
import com.example.data.database.models.User
import com.example.data.database.models.Users
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class UsersDaoImpl : DatabaseDao.UsersDao {
    private fun resultRowToArticle(row: ResultRow) = User(
        id = row[Users.id],
        email = row[Users.email],
        fullName = row[Users.fullName],
        passwordHash = row[Users.passwordHash],
        isClassTeacher = row[Users.isClassTeacher],
    )

    override suspend fun allUsers(): List<User> = DatabaseSingleton.dbQuery {
        Users.selectAll().map(::resultRowToArticle)
    }

//    override suspend fun editPasswordHash(newPasswordHash: String): Boolean {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun addNewUser(email: String, fullName: String, passwordHash: String): User {
//        TODO("Not yet implemented")
//    }
}