package com.aashu.notificationinbox.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val ScreenBackground = Color(0xFFF7F8FC)
private val PrimaryBlue = Color(0xFF4355D8)
private val PrimaryLight = Color(0xFFEEF0FF)
private val MainText = Color(0xFF20243A)
private val SecondaryText = Color(0xFF858BA0)
private val BorderColor = Color(0xFFEAECF3)
private val UnreadBackground = Color(0xFFF8F9FF)

private data class NotificationItem(
    val id: String,
    val title: String,
    val description: String,
    val timestamp: String,
    val isRead: Boolean
)

private val sampleNotifications = listOf(
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

@Composable
fun InboxScreen() {

    val notifications = remember { sampleNotifications }

    val unreadCount = notifications.count { !it.isRead }

    Scaffold(
        containerColor = ScreenBackground
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(
                horizontal = 20.dp,
                vertical = 20.dp
            ),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {

            item {
                InboxHeader(unreadCount = unreadCount)
            }

            item {
                SectionHeader(
                    totalCount = notifications.size
                )
            }

            items(
                items = notifications,
                key = { it.id }
            ) { notification ->

                NotificationCard(
                    notification = notification
                )
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "You're all caught up!",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    color = SecondaryText,
                    fontSize = 12.sp,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun InboxHeader(unreadCount: Int) {

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(46.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(PrimaryBlue),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "N",
                    color = Color.White,
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = "Notification",
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold,
                    color = MainText
                )

                Text(
                    text = "Your personal updates",
                    fontSize = 12.sp,
                    color = SecondaryText
                )
            }

            Surface(
                color = PrimaryLight,
                shape = RoundedCornerShape(30.dp)
            ) {

                Text(
                    text = "$unreadCount Unread",
                    modifier = Modifier.padding(
                        horizontal = 12.dp,
                        vertical = 8.dp
                    ),
                    color = PrimaryBlue,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {

            Text(
                text = "Notification Inbox",
                fontSize = 27.sp,
                fontWeight = FontWeight.Bold,
                color = MainText
            )

            Text(
                text = "All your important updates, in one place.",
                fontSize = 14.sp,
                color = SecondaryText
            )
        }
    }
}

@Composable
private fun SectionHeader(totalCount: Int) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = "Recent notifications",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = MainText
        )

        Text(
            text = "$totalCount notifications",
            fontSize = 12.sp,
            color = SecondaryText
        )
    }
}

@Composable
private fun NotificationCard(
    notification: NotificationItem
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = BorderColor,
                shape = RoundedCornerShape(18.dp)
            ),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (!notification.isRead) {
                UnreadBackground
            } else {
                Color.White
            }
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {

        Row(
            modifier = Modifier.padding(13.dp),
            horizontalArrangement = Arrangement.spacedBy(13.dp)
        ) {

            BannerPlaceholder()

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(7.dp)
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = notification.title,
                        modifier = Modifier.weight(1f),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = MainText,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    if (!notification.isRead) {

                        Spacer(modifier = Modifier.width(6.dp))

                        Box(
                            modifier = Modifier
                                .size(9.dp)
                                .clip(CircleShape)
                                .background(PrimaryBlue)
                        )
                    }
                }

                Text(
                    text = notification.description,
                    fontSize = 12.sp,
                    color = SecondaryText,
                    lineHeight = 17.sp,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(2.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = notification.timestamp,
                        fontSize = 10.sp,
                        color = SecondaryText
                    )

                    Surface(
                        color = if (notification.isRead) {
                            Color(0xFFF0F1F5)
                        } else {
                            PrimaryLight
                        },
                        shape = RoundedCornerShape(20.dp)
                    ) {

                        Text(
                            text = if (notification.isRead) "Read" else "Unread",
                            modifier = Modifier.padding(
                                horizontal = 9.dp,
                                vertical = 4.dp
                            ),
                            color = if (notification.isRead) {
                                SecondaryText
                            } else {
                                PrimaryBlue
                            },
                            fontSize = 10.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun BannerPlaceholder() {

    Box(
        modifier = Modifier
            .size(width = 86.dp, height = 112.dp)
            .clip(RoundedCornerShape(13.dp))
            .background(PrimaryLight),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {

            Text(
                text = "▧",
                fontSize = 30.sp,
                color = PrimaryBlue
            )

            Text(
                text = "BANNER",
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryBlue
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun InboxScreenPreview() {

    MaterialTheme {
        InboxScreen()
    }
}
