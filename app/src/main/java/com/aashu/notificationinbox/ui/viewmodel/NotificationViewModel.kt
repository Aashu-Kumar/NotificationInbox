package com.aashu.notificationinbox.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.aashu.notificationinbox.data.local.NotificationEntity
import com.aashu.notificationinbox.data.repository.NotificationRepository
import com.aashu.notificationinbox.ui.model.NotificationItem
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NotificationViewModel(
    private val repository: NotificationRepository
) : ViewModel() {

    // Convert database notifications into the model used by our UI.
    val notifications: StateFlow<List<NotificationItem>> =
        repository.allNotifications
            .map { entities ->
                entities.map { entity ->
                    entity.toNotificationItem()
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    // Observe one notification by its unique ID.
    fun getNotificationById(notificationId: String) =
        repository.getNotificationById(notificationId)
            .map { entity ->
                entity?.toNotificationItem()
            }

    // Save a notification in Room.
    fun insertNotification(notification: NotificationItem) {
        viewModelScope.launch {
            repository.insertNotification(
                NotificationEntity(
                    id = notification.id,
                    title = notification.title,
                    description = notification.description,
                    timestamp = notification.timestamp,
                    isRead = notification.isRead,
                    bannerImg = notification.bannerImg
                )
            )
        }
    }

    // Mark a notification as read.
    fun markAsRead(notificationId: String) {
        viewModelScope.launch {
            repository.markAsRead(notificationId)
        }
    }

    // Delete one notification.
    fun deleteNotification(notificationId: String) {
        viewModelScope.launch {
            repository.deleteNotification(notificationId)
        }
    }

    // Delete all notifications.
    fun deleteAllNotifications() {
        viewModelScope.launch {
            repository.deleteAllNotifications()
        }
    }

    companion object {

        fun factory(
            repository: NotificationRepository
        ): ViewModelProvider.Factory {

            return object : ViewModelProvider.Factory {

                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(
                    modelClass: Class<T>
                ): T {

                    if (modelClass.isAssignableFrom(
                            NotificationViewModel::class.java
                        )
                    ) {
                        return NotificationViewModel(repository) as T
                    }

                    throw IllegalArgumentException(
                        "Unknown ViewModel class: ${modelClass.name}"
                    )
                }
            }
        }
    }

    // Insert sample notifications only when the database is empty.
    fun seedSampleNotificationsIfEmpty() {
        viewModelScope.launch {
            if (repository.getNotificationCount() == 0) {
                com.aashu.notificationinbox.ui.model.SampleNotifications.items
                    .forEach { notification ->
                        repository.insertNotification(
                            NotificationEntity(
                                id = notification.id,
                                title = notification.title,
                                description = notification.description,
                                timestamp = notification.timestamp,
                                isRead = notification.isRead,
                                bannerImg = notification.bannerImg
                            )
                        )
                    }
            }
        }
    }
}

// Convert Room Entity into the existing UI model.
private fun NotificationEntity.toNotificationItem(): NotificationItem {
    return NotificationItem(
        id = id,
        title = title,
        description = description,
        timestamp = timestamp,
        isRead = isRead,
        bannerImg = bannerImg
    )
}