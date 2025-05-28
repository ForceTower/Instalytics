package dev.forcetower.instalytics.android.ui.navigation.home

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dev.forcetower.instalytics.android.ui.InstalyticsActivity

private const val TAG = "HomeActivity"

internal class HomeActivity : InstalyticsActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
//        if (false isTransitioningTo true) {
//
//        }

        setContent {
            InstalyticsHome()
        }
    }
}