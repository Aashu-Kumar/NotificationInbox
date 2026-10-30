package com.aashu.notificationinbox.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notifications")
data class NotificationEntity(

    @PrimaryKey
    val id: String,

    val title: String,

    val description: String,

    val timestamp: String,

    val isRead: Boolean = false,

    val bannerImg: String? = null
)
