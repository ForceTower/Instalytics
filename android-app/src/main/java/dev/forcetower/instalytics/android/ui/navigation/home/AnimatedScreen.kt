package dev.forcetower.instalytics.android.ui.navigation.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun <T : Any> AnimatedScreen(
    thisRoute: T,
    selectedRoute: T,
    content: @Composable () -> Unit
) {
    val visible = thisRoute == selectedRoute
    AnimatedVisibility(
        visible = visible,
        enter = scaleIn(
            initialScale = 0.94f,
            animationSpec = tween(300, easing = CubicBezierEasing(0.2f, 0f, 0f, 1f))
        ) + fadeIn(
            animationSpec = tween(300, easing = CubicBezierEasing(0.2f, 0f, 0f, 1f))
        ),
        exit = scaleOut(
            targetScale = 1.02f,
            animationSpec = tween(300, easing = CubicBezierEasing(0.2f, 0f, 0f, 1f))
        ) + fadeOut(
            animationSpec = tween(300, easing = CubicBezierEasing(0.2f, 0f, 0f, 1f))
        )
    ) {
        content()
    }
}