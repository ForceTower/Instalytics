package dev.forcetower.instalytics.android.ui.navigation.account.facebook

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Scaffold
import dev.forcetower.instalytics.android.ui.InstalyticsActivity
import dev.forcetower.instalytics.android.ui.theme.InstalyticsTheme

class FacebookLoginActivity : InstalyticsActivity() {
    private val viewModel by viewModels<FacebookLoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            InstalyticsTheme {
                Scaffold { _ ->
                    FacebookLoginScreen(viewModel)
                }
            }
        }
    }
}