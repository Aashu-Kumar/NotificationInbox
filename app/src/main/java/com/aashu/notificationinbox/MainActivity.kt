
package com.aashu.notificationinbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.aashu.notificationinbox.ui.screens.InboxScreen
import com.aashu.notificationinbox.ui.theme.NotificationInboxTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            NotificationInboxTheme(
                darkTheme = false,
                dynamicColor = false
            ) {

                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {

                    InboxScreen()
                }
            }
        }
    }
}
