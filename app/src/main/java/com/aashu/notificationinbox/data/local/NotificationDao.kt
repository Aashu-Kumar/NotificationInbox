package com.aashu.notificationinbox.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NotificationDao {

    // Observe all notifications, newest first.
    @Query("SELECT * FROM notifications ORDER BY timestamp DESC")
    fun getAllNotifications(): Flow<List<NotificationEntity>>

    // Observe a particular notification using its unique ID.
    @Query("SELECT * FROM notifications WHERE id = :notificationId")
    fun getNotificationById(
        notificationId: String
    ): Flow<NotificationEntity?>

    // Insert a notification only if its ID does not already exist.
    // Existing notifications will not be overwritten.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNotification(notification: NotificationEntity)

    // Mark a notification as read.
    @Query("UPDATE notifications SET isRead = 1 WHERE id = :notificationId")
    suspend fun markAsRead(notificationId: String)

    // Delete one notification.
    @Query("DELETE FROM notifications WHERE id = :notificationId")
    suspend fun deleteNotification(notificationId: String)

    // Delete all notifications.
    @Query("DELETE FROM notifications")
    suspend fun deleteAllNotifications()

    @Query("SELECT COUNT(*) FROM notifications")
    suspend fun getNotificationCount(): Int
}