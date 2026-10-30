package com.aashu.notificationinbox.data.repository

import com.aashu.notificationinbox.data.local.NotificationDao
import com.aashu.notificationinbox.data.local.NotificationEntity
import kotlinx.coroutines.flow.Flow

class NotificationRepository(
    private val notificationDao: NotificationDao
) {

    // Get all notifications from the database.
    val allNotifications: Flow<List<NotificationEntity>> =
        notificationDao.getAllNotifications()

    // Get a particular notification using its ID.
    fun getNotificationById(
        notificationId: String
    ): Flow<NotificationEntity?> {
        return notificationDao.getNotificationById(notificationId)
    }

    // Save a notification.
    suspend fun insertNotification(
        notification: NotificationEntity
    ) {
        notificationDao.insertNotification(notification)
    }

    // Mark a notification as read.
    suspend fun markAsRead(notificationId: String) {
        notificationDao.markAsRead(notificationId)
    }

    // Delete one notification.
    suspend fun deleteNotification(notificationId: String) {
        notificationDao.deleteNotification(notificationId)
    }

    // Delete all notifications.
    suspend fun deleteAllNotifications() {
        notificationDao.deleteAllNotifications()
    }

    suspend fun getNotificationCount(): Int {
        return notificationDao.getNotificationCount()
    }
}