package com.aashu.notificationinbox

import android.app.Application
import com.aashu.notificationinbox.data.local.NotificationDatabase
import com.aashu.notificationinbox.data.repository.NotificationRepository

class NotificationInboxApplication : Application() {

    val database by lazy {
        NotificationDatabase.getDatabase(this)
    }

    val repository by lazy {
        NotificationRepository(database.notificationDao())
    }
}