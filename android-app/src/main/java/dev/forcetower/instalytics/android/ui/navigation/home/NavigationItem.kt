package dev.forcetower.instalytics.android.ui.navigation.home

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem<T : Any>(
    @StringRes
    val title: Int,
    val icon: ImageVector,
    val route: T
)