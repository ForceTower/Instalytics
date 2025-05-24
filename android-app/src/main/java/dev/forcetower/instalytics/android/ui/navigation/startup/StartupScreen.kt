package dev.forcetower.instalytics.android.ui.navigation.startup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import dev.forcetower.instalytics.android.ui.navigation.ILNavigation
import dev.forcetower.instalytics.android.ui.navigation.account.instalytics.email.InstalyticsLogin
import dev.forcetower.instalytics.android.ui.theme.InstalyticsTheme
import kotlinx.coroutines.delay
import kotlinx.serialization.Serializable

@Serializable
object Startup : ILNavigation

@Composable
fun StartupScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }

    LaunchedEffect(key1 = true) {
        delay(1000L)
        navController.navigate(InstalyticsLogin) {
            popUpTo(Startup) {
                inclusive = true
            }
        }
    }
}

@Preview
@Composable
fun PreviewStartupScreen() {
    InstalyticsTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            StartupScreen(navController = NavController(LocalContext.current))
        }
    }
}