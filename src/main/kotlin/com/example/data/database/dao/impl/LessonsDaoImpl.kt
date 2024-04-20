package com.example.data.database.dao.impl

import com.example.data.database.dao.DatabaseSingleton.dbQuery
import com.example.data.database.dao.DatabaseDao
import com.example.data.database.models.Lesson
import com.example.data.database.models.Lessons
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class LessonsDaoImpl : DatabaseDao.LessonsDao {
    private fun resultRowToArticle(row: ResultRow) = Lesson(
        id = row[Lessons.id],
        audience = row[Lessons.audience],
        startTime = row[Lessons.startTime],
        endTime = row[Lessons.endTime],
        theme = row[Lessons.theme],
    )

    override suspend fun allLessons(): List<Lesson> = dbQuery {
        Lessons.selectAll().map(::resultRowToArticle)
    }

//    override suspend fun lesson(id: Int): Lesson? = dbQuery {
//        Lessons
//            .select { Lessons.id eq id }
//            .map(::resultRowToArticle)
//            .singleOrNull()
//    }

//    override suspend fun getLessonsByIdList(idList: List<Int>): List<Lesson> {
//        val lessonsList = mutableListOf<Lesson>()
//        idList.forEach { id ->
//            lesson(id)?.let { lessonsList.add(it) }
//        }
//        return lessonsList
//    }
//
//    override suspend fun addNewLesson(title: String, body: String): Lesson? = dbQuery {
//        val insertStatement = Lessons.insert {
//            it[Lessons.theme] = title
//        }
//        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToArticle)
//    }
//
//    override suspend fun editLesson(id: Int, title: String, body: String): Boolean = dbQuery {
//        Lessons.update({ Lessons.id eq id }) {
//            it[Lessons.title] = title
//        } > 0
//    }
//
//    override suspend fun deleteLesson(id: Int) = dbQuery {
//        Lessons.deleteWhere { Lessons.id eq id } > 0
//    }

}