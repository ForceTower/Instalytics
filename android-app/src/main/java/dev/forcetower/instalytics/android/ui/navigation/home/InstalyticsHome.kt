package dev.forcetower.instalytics.android.ui.navigation.home

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.forcetower.instalytics.android.R
import dev.forcetower.instalytics.android.ui.navigation.insights.Insights
import dev.forcetower.instalytics.android.ui.navigation.profile.Profile
import dev.forcetower.instalytics.android.ui.navigation.search.Search
import dev.forcetower.instalytics.android.ui.theme.InstalyticsTheme

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
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun InstalyticsHome() {
    InstalyticsTheme {
        val driveEasing = CubicBezierEasing(0.2f, 0f, 0f, 1f)
        val navController = rememberNavController()
        val selectedNavigationIndex = rememberSaveable {
            mutableIntStateOf(0)
        }

        Scaffold(
            bottomBar = { HomeBottomBar(navController, selectedNavigationIndex) },
            modifier = Modifier
                .fillMaxSize(),
        ) { paddingValues ->
            NavHost(navController = navController, startDestination = HomeScreen.Insight) {
                composable<HomeScreen.Insight> { AnimatedScreen(HomeScreen.Insight, navItems[selectedNavigationIndex.intValue].route) { Insights(paddingValues) } }
                composable<HomeScreen.Search> { AnimatedScreen(HomeScreen.Search, navItems[selectedNavigationIndex.intValue].route) { Search(paddingValues) } }
                composable<HomeScreen.Profile> { AnimatedScreen(HomeScreen.Profile, navItems[selectedNavigationIndex.intValue].route) { Profile(paddingValues) } }
            }
        }
    }
}

@Composable
fun HomeBottomBar(
    navController: NavController,
    selectedNavigationIndex: MutableIntState
) {
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

@Composable
@Preview
fun InstalyticsHomePreview() {
    InstalyticsHome()
}