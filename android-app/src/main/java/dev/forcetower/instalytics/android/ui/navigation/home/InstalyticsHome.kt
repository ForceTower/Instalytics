package dev.forcetower.instalytics.android.ui.navigation.home

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.forcetower.instalytics.android.R
import dev.forcetower.instalytics.android.ui.navigation.profile.Profile
import dev.forcetower.instalytics.android.ui.navigation.profile.ProfileScreen
import dev.forcetower.instalytics.android.ui.theme.InstalyticsTheme

sealed interface HomeScreen {
    object Profile : HomeScreen
    object Search : HomeScreen
    object Insight : HomeScreen
}

data class NavigationItem<T : Any>(
    @StringRes
    val title: Int,
    val icon: ImageVector,
    val route: T
)

val navItems = listOf(
    NavigationItem(
        title = R.string.home_insight,
        icon = Icons.Default.Star,
        route = HomeScreen.Insight
    ),
    NavigationItem(
        title = R.string.home_search,
        icon = Icons.Default.Search,
        route = HomeScreen.Search
    ),
    NavigationItem(
        title = R.string.home_profile,
        icon = Icons.Default.Person,
        route = HomeScreen.Profile
    ),
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun InstalyticsHome() {
    InstalyticsTheme {
        val navController = rememberNavController()
        Scaffold(
            bottomBar = { HomeBottomBar() },
            modifier = Modifier
                .fillMaxSize(),
        ) { paddingValues ->
            NavHost(navController = navController, startDestination = ProfileScreen) {
                composable<ProfileScreen> { Profile(paddingValues) }
            }
        }
    }
}

@Composable
fun HomeBottomBar() {
    val selectedNavigationIndex = rememberSaveable {
        mutableIntStateOf(2)
    }

    NavigationBar {
        navItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selectedNavigationIndex.intValue,
                onClick = { selectedNavigationIndex.intValue = index },
                icon = { Icon(imageVector = item.icon, contentDescription = stringResource(item.title)) },
                label = { Text(text = stringResource(id = item.title)) },
            )
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Preview
fun InstalyticsHomePreview() {
    InstalyticsHome()
}