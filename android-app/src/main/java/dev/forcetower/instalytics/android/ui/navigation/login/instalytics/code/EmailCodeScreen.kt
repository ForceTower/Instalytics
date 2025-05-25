package dev.forcetower.instalytics.android.ui.navigation.login.instalytics.code

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import dev.forcetower.instalytics.android.R
import dev.forcetower.instalytics.android.ui.components.pin.PinEntryView
import dev.forcetower.instalytics.android.ui.theme.InstalyticsTheme
import kotlinx.serialization.Serializable

@Serializable
data class EmailCode(val email: String, val security: String)

@Composable
fun EmailCodeScreen(
    params: EmailCode
) {
    var code = remember { mutableStateOf("") }
    val scrollState = rememberScrollState()
    val loading = remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
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
            text = "Enter code received",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal))
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
        )

        PinEntryView(
            pinLength = 6,
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal))
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
        ) { pin ->
            code.value = pin
        }

        if (loading.value) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal))
                    .padding(top = 32.dp, start = 16.dp, end = 16.dp)
            )
        }

        Spacer(
            modifier = Modifier
                .consumeWindowInsets(WindowInsets.systemBars.only(WindowInsetsSides.Bottom))
                .padding(bottom = 16.dp)
        )
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun EmailCodeScreenPreview() {
    InstalyticsTheme {
        Scaffold { _ ->
            EmailCodeScreen(EmailCode(email = "email@email.com", security = "dihfsodihfg"))
        }
    }
}