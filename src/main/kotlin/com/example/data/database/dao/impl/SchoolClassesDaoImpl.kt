package com.example.data.database.dao.impl

import com.example.data.database.DatabaseSingleton
import com.example.data.database.dao.DatabaseDao.SchoolClassesDao
import com.example.data.database.models.SchoolClass
import com.example.data.database.models.SchoolClasses
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class SchoolClassesDaoImpl : SchoolClassesDao {
    override suspend fun allSchoolClasses(): List<SchoolClass> = DatabaseSingleton.dbQuery {
        SchoolClasses.selectAll().map(::mapToSchoolClass)
    }

    private fun mapToSchoolClass(row: ResultRow) = SchoolClass(
        id = row[SchoolClasses.id],
        className = row[SchoolClasses.className]
    )
}