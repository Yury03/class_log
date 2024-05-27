package com.example.data.database.dao.impl

import com.example.data.database.DatabaseSingleton
import com.example.data.database.dao.DatabaseDao.NotificationsDao
import com.example.data.database.models.Notification
import com.example.data.database.models.Notifications
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class NotificationsDaoImpl : NotificationsDao {
    override suspend fun allNotifications(): List<Notification> = DatabaseSingleton.dbQuery {
        Notifications.selectAll().map(::mapToNotification)
    }

    private fun mapToNotification(row: ResultRow) = Notification(
        id = row[Notifications.id],
        title = row[Notifications.title],
        body = row[Notifications.body],
        time = row[Notifications.time],
        wasRead = row[Notifications.wasRead],
    )

}