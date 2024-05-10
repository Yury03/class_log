package com.example.data.database.dao.impl

import com.example.data.database.dao.DatabaseDao.KeysDao
import com.example.data.database.models.Key
import com.example.data.database.models.Keys
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class KeysDaoImpl : KeysDao {
    private fun mapToKey(row: ResultRow) = Key(
        id = row[Keys.id],
        key = row[Keys.key],
        validity = row[Keys.validity],
    )
    private fun getValidity() = Date().time + 24 * 60 * 60 * 1000L * VALIDITY_DAYS
    private fun getNewKey() = UUID.randomUUID().toString()
    private fun getCurrentDate() = Date().time

    /**
     * Метод выполняет логику проверки валидности ключа
     * */
    override suspend fun getKeyByUserId(userId: Int): Key? {
        // todo метод должен удалять невалидные ключи
        return getKeyByUserIdPrivate(userId = userId) ?: run {
            val insertStatement = Keys.insert {
                it[Keys.userId] = userId
                it[key] = getNewKey()
                it[validity] = getValidity()
            }
            insertStatement.resultedValues?.singleOrNull()?.let(::mapToKey)
        }
    }

    /**
     * Метод возвращает ключ по [userId], либо возвращает null
     * */
    private fun getKeyByUserIdPrivate(userId: Int): Key? =
        transaction {
            val currentDate = getCurrentDate()
            Keys.select {
                (Keys.userId eq userId) and (Keys.validity greaterEq currentDate)
            }.singleOrNull()?.let { key ->
                mapToKey(key)
            }
        }

    override suspend fun deleteKey(userId: Int): Boolean {
        TODO("Not yet implemented")
    }

    private companion object {
        const val VALIDITY_DAYS = 10 // срок действия ключа 10 дней
    }
}