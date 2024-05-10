package com.example.data.database.dao.impl

import com.example.data.database.DatabaseSingleton
import com.example.data.database.dao.DatabaseDao.StudentsDao
import com.example.data.database.models.Student
import com.example.data.database.models.Students
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class StudentsDaoImpl : StudentsDao {
    override suspend fun allStudents(): List<Student> = DatabaseSingleton.dbQuery {
        Students.selectAll().map(::mapToStudent)
    }

    private fun mapToStudent(row: ResultRow) = Student(
        id = row[Students.id],
        fullName = row[Students.fullName],
        parentPhoneNumber = row[Students.parentPhoneNumber],
        phoneNumber = row[Students.phoneNumber],
    )
}