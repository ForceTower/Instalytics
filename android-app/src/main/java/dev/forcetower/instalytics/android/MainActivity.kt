package dev.forcetower.instalytics.android

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.WindowCompat
import com.facebook.AccessToken
import com.facebook.login.widget.LoginButton
import dev.forcetower.instalytics.android.ui.InstalyticsApp
import dev.forcetower.instalytics.android.ui.theme.InstalyticsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        val accessToken = AccessToken.getCurrentAccessToken()
        println("Current token: ${accessToken?.expires} ${accessToken?.isInstagramToken} ${accessToken?.userId} ${accessToken?.graphDomain} -> ${accessToken?.token}")
        setContent {
            InstalyticsApp()
        }
    }
}

@Composable
fun FacebookLogin() {
    AndroidView(
        factory = { context ->
            LoginButton(context, null).apply {
                setPermissions(
                    "instagram_basic",
                    "instagram_content_publish",
                    "instagram_manage_comments",
                    "instagram_manage_insights",
                    "instagram_manage_messages",
                    "pages_show_list",
                    "pages_read_engagement",
                    "ads_management",
                    "ads_read"
                )
            }
        },
        modifier = Modifier.padding(16.dp),
        update = { view ->

        }
    )
}
