
package com.aashu.notificationinbox.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aashu.notificationinbox.NotificationInboxApplication
import com.aashu.notificationinbox.ui.screens.InboxScreen
import com.aashu.notificationinbox.ui.screens.NotificationDetailsScreen
import com.aashu.notificationinbox.ui.viewmodel.NotificationViewModel

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

    // Get the Application instance.
    val context = LocalContext.current
    val application = context.applicationContext as NotificationInboxApplication

    // Create the ViewModel using our Repository.
    val notificationViewModel: NotificationViewModel = viewModel(
        factory = NotificationViewModel.factory(
            application.repository
        )
    )

    // Observe notifications stored in Room.
    val notifications by notificationViewModel.notifications.collectAsState()

    // Insert sample notifications only if the database is empty.
    LaunchedEffect(Unit) {
        notificationViewModel.seedSampleNotificationsIfEmpty()
    }

    NavHost(
        navController = navController,
        startDestination = AppRoutes.INBOX
    ) {

        // Inbox screen.
        composable(AppRoutes.INBOX) {

            InboxScreen(
                notifications = notifications,
                onNotificationClick = { notificationId ->

                    // Mark as read in the database.
                    notificationViewModel.markAsRead(notificationId)

                    // Navigate to notification details.
                    navController.navigate(
                        AppRoutes.details(notificationId)
                    )
                }
            )
        }

        // Notification details screen.
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

            if (notificationId != null) {

                // Observe the selected notification from Room.
                val notificationFlow = remember(
                    notificationViewModel,
                    notificationId
                ) {
                    notificationViewModel.getNotificationById(
                        notificationId
                    )
                }

                val selectedNotification by notificationFlow.collectAsState(
                    initial = null
                )

                NotificationDetailsScreen(
                    notification = selectedNotification,
                    onBackClick = {
                        navController.popBackStack()
                    }
                )

            } else {

                NotificationDetailsScreen(
                    notification = null,
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}
