package com.example.data.database.dao.impl

import com.example.data.database.DatabaseSingleton.dbQuery
import com.example.data.database.dao.DatabaseDao.UsersDao
import com.example.data.database.models.User
import com.example.data.database.models.Users
import com.example.data.database.utils.hashPassword
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class UsersDaoImpl : UsersDao {
    private fun mapToUser(row: ResultRow) = User(
        id = row[Users.id],
        email = row[Users.email],
        fullName = row[Users.fullName],
        passwordHash = row[Users.passwordHash],
        isClassTeacher = row[Users.isClassTeacher],
    )

    override suspend fun allUsers(): List<User> = dbQuery {
        Users.selectAll().map(::mapToUser)
    }

    override suspend fun editPassword(id: Int, newPassword: String): Boolean = dbQuery {
        Users.update({ Users.id eq id }) {
            it[passwordHash] = hashPassword(newPassword)
        } > 0
    }

    override suspend fun getUserIdWithAuthentication(email: String, inputPassword: String): Int? {
        val passwordHash = hashPassword(inputPassword)
        return transaction {
            Users.select { (Users.email eq email) and (Users.passwordHash eq passwordHash) }
                .singleOrNull()?.let { it[Users.id] }
        }
    }

    override suspend fun addNewUser(email: String, fullName: String, password: String, isClassTeacher: Boolean) =
        dbQuery {
            val insertStatement = Users.insert {
                it[Users.email] = email
                it[Users.fullName] = fullName
                it[Users.isClassTeacher] = isClassTeacher
                it[passwordHash] = hashPassword(password)
            }
            insertStatement.resultedValues?.singleOrNull()?.let(::mapToUser)
        }

}