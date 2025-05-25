package dev.forcetower.instalytics.android.ui.navigation.home

import kotlinx.serialization.Serializable

@Serializable
sealed interface HomeScreen {
    @Serializable object Profile : HomeScreen
    @Serializable object Search : HomeScreen
    @Serializable object Insight : HomeScreen
}