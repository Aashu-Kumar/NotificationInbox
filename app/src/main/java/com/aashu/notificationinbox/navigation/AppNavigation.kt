
package com.aashu.notificationinbox.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aashu.notificationinbox.ui.model.NotificationItem
import com.aashu.notificationinbox.ui.model.SampleNotifications
import com.aashu.notificationinbox.ui.screens.InboxScreen
import com.aashu.notificationinbox.ui.screens.NotificationDetailsScreen

private object AppRoutes {
    const val INBOX = "inbox"
    const val DETAILS = "details/{notificationId}"

    fun details(notificationId: String): String {
        return "details/${Uri.encode(notificationId)}"
    }
}

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    val notifications = remember {
        mutableStateListOf<NotificationItem>().apply {
            addAll(SampleNotifications.items)
        }
    }

    NavHost(
        navController = navController,
        startDestination = AppRoutes.INBOX
    ) {

        composable(AppRoutes.INBOX) {

            InboxScreen(
                notifications = notifications,
                onNotificationClick = { notificationId ->

                    val index = notifications.indexOfFirst {
                        it.id == notificationId
                    }

                    if (index != -1) {

                        val selectedNotification = notifications[index]

                        // Mark as read before opening details.
                        if (!selectedNotification.isRead) {

                            notifications[index] =
                                selectedNotification.copy(isRead = true)
                        }

                        navController.navigate(
                            AppRoutes.details(notificationId)
                        )
                    }
                }
            )
        }

        composable(
            route = AppRoutes.DETAILS,
            arguments = listOf(
                navArgument("notificationId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->

            val notificationId = backStackEntry.arguments
                ?.getString("notificationId")

            val selectedNotification = notifications.firstOrNull {
                it.id == notificationId
            }

            NotificationDetailsScreen(
                notification = selectedNotification,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
