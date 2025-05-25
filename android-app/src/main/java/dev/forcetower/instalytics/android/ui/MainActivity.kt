package dev.forcetower.instalytics.android.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.facebook.AccessToken
import com.facebook.login.widget.LoginButton
import dev.forcetower.instalytics.di.AndroidApplicationComponent
import dev.forcetower.instalytics.di.create

internal class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        val applicationComponent = AndroidApplicationComponent.create()
        applicationComponent.thing.run()
        val accessToken = AccessToken.getCurrentAccessToken()
        setContent {
            Instalytics()
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
