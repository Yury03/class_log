package com.example.data.database.dao.impl

import com.example.data.database.dao.DatabaseSingleton
import com.example.data.database.dao.DatabaseDao
import com.example.data.database.models.Student
import com.example.data.database.models.Students
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class StudentsDaoImpl : DatabaseDao.StudentsDao {
    override suspend fun allStudents(): List<Student> = DatabaseSingleton.dbQuery {
        Students.selectAll().map(::resultRowToArticle)
    }

    private fun resultRowToArticle(row: ResultRow) = Student(
        id = row[Students.id],
        fullName = row[Students.fullName],
        parentPhoneNumber = row[Students.parentPhoneNumber],
        phoneNumber = row[Students.phoneNumber],
    )
}