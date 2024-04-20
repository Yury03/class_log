package com.example.data.database.dao.impl

import com.example.data.database.dao.DatabaseSingleton
import com.example.data.database.dao.DatabaseDao
import com.example.data.database.models.Notification
import com.example.data.database.models.Notifications
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class NotificationsDaoImpl : DatabaseDao.NotificationsDao {
    override suspend fun allNotifications(): List<Notification> = DatabaseSingleton.dbQuery {
        Notifications.selectAll().map(::resultRowToArticle)
    }

    private fun resultRowToArticle(row: ResultRow) = Notification(
        id = row[Notifications.id],
        title = row[Notifications.title],
        body = row[Notifications.body],
        time = row[Notifications.time],
        wasRead = row[Notifications.wasRead],
    )

}