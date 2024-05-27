package com.example.data.database.dao.impl

import com.example.data.database.DatabaseSingleton
import com.example.data.database.dao.DatabaseDao.SubjectsDao
import com.example.data.database.models.Subject
import com.example.data.database.models.Subjects
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class SubjectsDaoImpl : SubjectsDao {
    override suspend fun allSubjects(): List<Subject> = DatabaseSingleton.dbQuery {
        Subjects.selectAll().map(::mapToSubject)
    }

    private fun mapToSubject(row: ResultRow) = Subject(
        id = row[Subjects.id],
        title = row[Subjects.title]
    )
}