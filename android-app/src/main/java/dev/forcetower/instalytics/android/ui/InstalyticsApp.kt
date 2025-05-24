package dev.forcetower.instalytics.android.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import dev.forcetower.instalytics.android.ui.navigation.account.facebook.FacebookLogin
import dev.forcetower.instalytics.android.ui.navigation.account.facebook.FacebookLoginScreen
import dev.forcetower.instalytics.android.ui.navigation.account.instalytics.code.EmailCode
import dev.forcetower.instalytics.android.ui.navigation.account.instalytics.code.EmailCodeScreen
import dev.forcetower.instalytics.android.ui.navigation.account.instalytics.email.InstalyticsLogin
import dev.forcetower.instalytics.android.ui.navigation.account.instalytics.email.EmailScreen
import dev.forcetower.instalytics.android.ui.navigation.onboarding.Onboarding
import dev.forcetower.instalytics.android.ui.navigation.onboarding.OnboardingScreen
import dev.forcetower.instalytics.android.ui.navigation.startup.Startup
import dev.forcetower.instalytics.android.ui.navigation.startup.StartupScreen
import dev.forcetower.instalytics.android.ui.theme.InstalyticsTheme

@Preview
@Composable
fun InstalyticsApp() {
    InstalyticsTheme {
        val navController = rememberNavController()

        Scaffold { paddingValues ->
            NavHost(navController = navController, startDestination = Startup) {
                addMainGraph(navController, paddingValues = paddingValues)
            }
        }
    }
}

fun NavGraphBuilder.addMainGraph(navController: NavController, paddingValues: PaddingValues) {
    composable<Startup> { from -> StartupScreen(navController) }
    composable<Onboarding> { OnboardingScreen() }
    composable<InstalyticsLogin> { EmailScreen(navController) }
    composable<EmailCode> { backStackEntry ->
        val params = backStackEntry.toRoute<EmailCode>()
        EmailCodeScreen(params)
    }
    composable<FacebookLogin> { FacebookLoginScreen() }
}