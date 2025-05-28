package dev.forcetower.instalytics.android.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import dev.forcetower.instalytics.data.storage.database.entity.InstagramAccount
import dev.forcetower.instalytics.di.AndroidApplicationComponent
import dev.forcetower.instalytics.di.create
import dev.forcetower.kmm.toolkit.logdog.logdog
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
private const val TAG = "MainActivity"

internal class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            Instalytics()
        }
    }
}
