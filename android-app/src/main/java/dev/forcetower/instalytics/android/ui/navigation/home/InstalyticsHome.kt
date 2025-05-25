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
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.forcetower.instalytics.android.R
import dev.forcetower.instalytics.android.ui.navigation.profile.Profile
import dev.forcetower.instalytics.android.ui.navigation.insights.Insights
import dev.forcetower.instalytics.android.ui.navigation.search.Search
import dev.forcetower.instalytics.android.ui.theme.InstalyticsTheme
import kotlinx.serialization.Serializable

@Serializable
sealed interface HomeScreen {
    @Serializable object Profile : HomeScreen
    @Serializable object Search : HomeScreen
    @Serializable object Insight : HomeScreen
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
            bottomBar = { HomeBottomBar(navController) },
            modifier = Modifier
                .fillMaxSize(),
        ) { paddingValues ->
            NavHost(navController = navController, startDestination = HomeScreen.Insight) {
                composable<HomeScreen.Insight> { Insights(paddingValues) }
                composable<HomeScreen.Search> { Search(paddingValues) }
                composable<HomeScreen.Profile> { Profile(paddingValues) }
            }
        }
    }
}

@Composable
fun HomeBottomBar(
    navController: NavController
) {
    val selectedNavigationIndex = rememberSaveable {
        mutableIntStateOf(0)
    }

    NavigationBar {
        navItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selectedNavigationIndex.intValue,
                onClick = {
                    selectedNavigationIndex.intValue = index
                    navController.navigate(item.route)
                },
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