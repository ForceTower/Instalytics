package dev.forcetower.instalytics.android.ui.navigation.account.facebook

import android.content.Intent
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.forcetower.instalytics.android.R
import dev.forcetower.instalytics.android.ui.navigation.InstalyticsNavigation
import dev.forcetower.instalytics.android.ui.navigation.home.HomeActivity
import dev.forcetower.instalytics.android.ui.theme.InstalyticsTheme
import kotlinx.serialization.Serializable

@Serializable
internal object FacebookLogin : InstalyticsNavigation

@Composable
internal fun FacebookLoginScreen(viewModel: FacebookLoginViewModel) {
    val activity = LocalActivity.current
    val scrollState = rememberScrollState()
    val loading = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.events.collect {
            when (it) {
                FacebookLoginEvent.LoginSuccess -> {
                    loading.value = false
                    requireNotNull(activity)
                    activity.startActivity(Intent(activity, HomeActivity::class.java))
                    activity.finish()
                }

                FacebookLoginEvent.LoginCanceled -> {
                    loading.value = false
                }
                FacebookLoginEvent.LoginFailed -> {
                    loading.value = false
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Image(
            painter = painterResource(R.mipmap.illustration_woman_email_code),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .consumeWindowInsets(WindowInsets.systemBars.only(WindowInsetsSides.Top))
                .fillMaxWidth()
                .heightIn(max = 370.dp)
        )

        Text(
            text = "Connect your Instagram Account",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal))
                .padding(top = 24.dp, start = 24.dp, end = 24.dp)
                .fillMaxWidth(),
        )

        Text(
            text = "To unlock insights and compare your stats, connect your Instagram. We'll never post or share your data.",
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal))
                .padding(top = 12.dp, start = 24.dp, end = 24.dp)
                .fillMaxWidth(),
        )

        if (!loading.value) {
            Button(
                onClick = {
                    loading.value = true
                    viewModel.login(requireNotNull(activity))
                },
                modifier = Modifier
                    .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal))
                    .padding(top = 16.dp, start = 24.dp, end = 24.dp)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = "Connect"
                )
            }
        }

        if (loading.value) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal))
                    .padding(top = 32.dp, start = 16.dp, end = 16.dp)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "We respect your privacy. Most functionality of the app works on device and most data never leave your phone.",
            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Light),
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal))
                .padding(bottom = 16.dp, start = 24.dp, end = 24.dp)
                .fillMaxWidth(),
        )

        Spacer(
            modifier = Modifier
                .navigationBarsPadding()
        )
    }
}

@Preview
@Composable
internal fun FacebookLoginScreenPreview() {
    InstalyticsTheme {
        val viewModel = viewModel<FacebookLoginViewModel>()
        Scaffold { _ ->
            FacebookLoginScreen(viewModel)
        }
    }
}