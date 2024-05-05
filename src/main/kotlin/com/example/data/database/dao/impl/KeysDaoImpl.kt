package com.example.data.database.dao.impl

import com.example.data.database.dao.DatabaseDao
import com.example.data.database.models.Key
import com.example.data.database.models.Keys
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import java.util.*

class KeysDaoImpl : DatabaseDao.KeysDao {
    private fun resultRowToArticle(row: ResultRow) = Key(
        id = row[Keys.id],
        key = row[Keys.key],
        validity = row[Keys.validity],
    )

    override suspend fun addNewKey(userId: Int): Key? {
        val insertStatement = Keys.insert {
            it[Keys.userId] = userId
            it[key] = getNewKey()
            it[validity] = getValidity()
        }
        return insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToArticle)
    }

    private fun getValidity() = Date().time + 24 * 60 * 60 * 1000L * VALIDITY_DAYS
    private fun getNewKey() = UUID.randomUUID().toString()

    override suspend fun deleteKey(userId: Int): Boolean {
        TODO("Not yet implemented")
    }

    private companion object {
        const val VALIDITY_DAYS = 10 // срок действия ключа 10 дней
    }
}