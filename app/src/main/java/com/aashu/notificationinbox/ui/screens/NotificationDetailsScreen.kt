
package com.aashu.notificationinbox.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aashu.notificationinbox.ui.model.NotificationItem

private val DetailsBackground = Color(0xFFF7F8FC)
private val DetailsBlue = Color(0xFF4355D8)
private val DetailsLightBlue = Color(0xFFEEF0FF)
private val DetailsText = Color(0xFF20243A)
private val DetailsSecondary = Color(0xFF858BA0)

@Composable
fun NotificationDetailsScreen(
    notification: NotificationItem?,
    onBackClick: () -> Unit
) {

    Scaffold(
        containerColor = DetailsBackground
    ) { innerPadding ->

        if (notification == null) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Notification unavailable",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = DetailsText
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "This notification could not be found.",
                    color = DetailsSecondary
                )

                Spacer(modifier = Modifier.height(20.dp))

                Surface(
                    onClick = onBackClick,
                    color = DetailsBlue,
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Go back",
                        modifier = Modifier.padding(
                            horizontal = 22.dp,
                            vertical = 12.dp
                        ),
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

        } else {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp, vertical = 16.dp)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Surface(
                        onClick = onBackClick,
                        color = Color.White,
                        shape = RoundedCornerShape(13.dp),
                        shadowElevation = 1.dp
                    ) {

                        Box(
                            modifier = Modifier.size(44.dp),
                            contentAlignment = Alignment.Center
                        ) {

                            Text(
                                text = "←",
                                fontSize = 25.sp,
                                color = DetailsText
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Text(
                        text = "Notification Details",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = DetailsText
                    )
                }

                Spacer(modifier = Modifier.height(26.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(210.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(DetailsLightBlue),
                    contentAlignment = Alignment.Center
                ) {

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {

                        Text(
                            text = "▧",
                            fontSize = 58.sp,
                            color = DetailsBlue
                        )

                        Text(
                            text = "NOTIFICATION BANNER",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = DetailsBlue
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Surface(
                    color = DetailsLightBlue,
                    shape = RoundedCornerShape(30.dp)
                ) {

                    Text(
                        text = "NOTIFICATION",
                        modifier = Modifier.padding(
                            horizontal = 12.dp,
                            vertical = 7.dp
                        ),
                        color = DetailsBlue,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = notification.title,
                    fontSize = 25.sp,
                    lineHeight = 33.sp,
                    fontWeight = FontWeight.Bold,
                    color = DetailsText
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = notification.description,
                    fontSize = 15.sp,
                    lineHeight = 25.sp,
                    color = DetailsSecondary
                )

                Spacer(modifier = Modifier.height(26.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 1.dp
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(18.dp),
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {

                        Text(
                            text = "Notification information",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = DetailsText
                        )

                        DetailRow(
                            label = "Received",
                            value = notification.timestamp
                        )

                        DetailRow(
                            label = "Notification ID",
                            value = notification.id
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text(
                                text = "Status",
                                modifier = Modifier.weight(1f),
                                fontSize = 13.sp,
                                color = DetailsSecondary
                            )

                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Box(
                                    modifier = Modifier
                                        .size(8.dp)
                                        .clip(CircleShape)
                                        .background(Color(0xFF16A36A))
                                )

                                Spacer(modifier = Modifier.width(7.dp))

                                Text(
                                    text = if (notification.isRead) {
                                        "Read"
                                    } else {
                                        "Unread"
                                    },
                                    fontSize = 13.sp,
                                    color = DetailsText,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "You're viewing the complete notification.",
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 12.sp,
                    color = DetailsSecondary,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
private fun DetailRow(
    label: String,
    value: String
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = label,
            modifier = Modifier.weight(1f),
            fontSize = 13.sp,
            color = DetailsSecondary
        )

        Text(
            text = value,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = DetailsText
        )
    }
}
