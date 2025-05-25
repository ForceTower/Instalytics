package dev.forcetower.instalytics.android.ui.navigation.home

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Scaffold
import dev.forcetower.instalytics.android.ui.theme.InstalyticsTheme

internal class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            InstalyticsTheme {
                Scaffold { _ ->
                    InstalyticsHome()
                }
            }
        }
    }
}