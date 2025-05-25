package dev.forcetower.instalytics.android.ui.navigation.account.instalytics.email

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.forcetower.instalytics.android.R
import dev.forcetower.instalytics.android.ui.components.text.InstalyticsTextField
import dev.forcetower.instalytics.android.ui.navigation.InstalyticsNavigation
import dev.forcetower.instalytics.android.ui.navigation.account.instalytics.code.EmailCode
import dev.forcetower.instalytics.android.ui.theme.InstalyticsTheme
import kotlinx.serialization.Serializable

@Serializable
object InstalyticsLogin : InstalyticsNavigation

@Composable
fun EmailScreen(
    navController: NavController
) {
    var email by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState),
    ) {
        Image(
            painter = painterResource(R.mipmap.illustration_woman_graph),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .consumeWindowInsets(WindowInsets.systemBars.only(WindowInsetsSides.Top))
                .fillMaxWidth()
                .heightIn(max = 370.dp)
        )

        Text(
            text = "Log in to your account",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal))
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
        )
        Text(
            text = "Type your email to receive a login code",
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal))
                .padding(top = 2.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
        )

        InstalyticsTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = "Email",
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal))
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
        )

        Button(
            onClick = {
                navController.navigate(EmailCode("email@email.com", "w9e8tr08gf4yrwufhw"))
            },
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal))
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
        ) {
            Text("Login")
        }

        Text(
            text = "Returning user?",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal))
                .padding(top = 12.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
        )

        TextButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal))
                .padding(top = 4.dp, start = 16.dp, end = 16.dp)
        ) {
            Text("Log in with passkey")
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
fun InstalyticsLoginScreenPreview() {
    InstalyticsTheme {
        Scaffold {  _ ->
            EmailScreen(NavController(LocalContext.current))
        }
    }
}