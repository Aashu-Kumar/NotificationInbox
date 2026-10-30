package com.aashu.notificationinbox.ui.model

data class NotificationItem(
    val id: String,
    val title: String,
    val description: String,
    val timestamp: String,
    val isRead: Boolean,
    val bannerImg: String? = null
)

object SampleNotifications {

    val items = listOf(
        NotificationItem(
            id = "notification_101",
            title = "Welcome to Notification Inbox!",
            description = "Your notification center is ready. Stay updated with all your important messages.",
            timestamp = "Today, 10:42 AM",
            isRead = false
        ),
        NotificationItem(
            id = "notification_102",
            title = "New Offer Available",
            description = "A special offer is waiting for you. Open the notification to discover more.",
            timestamp = "Today, 09:15 AM",
            isRead = false
        ),
        NotificationItem(
            id = "notification_103",
            title = "Account Update",
            description = "Your account information has been successfully updated.",
            timestamp = "Yesterday, 04:30 PM",
            isRead = true
        ),
        NotificationItem(
            id = "notification_104",
            title = "Important Announcement",
            description = "We have some exciting updates to share with you. Stay tuned!",
            timestamp = "Yesterday, 11:20 AM",
            isRead = false
        )
    )
}
