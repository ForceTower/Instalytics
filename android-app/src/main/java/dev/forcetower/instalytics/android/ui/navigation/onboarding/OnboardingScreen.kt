package dev.forcetower.instalytics.android.ui.navigation.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.forcetower.instalytics.android.ui.navigation.ILNavigation
import dev.forcetower.instalytics.android.ui.theme.InstalyticsTheme
import kotlinx.serialization.Serializable

@Serializable
object Onboarding : ILNavigation

@Composable
fun OnboardingScreen() {

}

@Preview
@Composable
fun OnboardingScreenPreview() {
    InstalyticsTheme {
        OnboardingScreen()
    }
}