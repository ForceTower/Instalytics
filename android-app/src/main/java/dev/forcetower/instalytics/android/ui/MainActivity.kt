package dev.forcetower.instalytics.android.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dev.forcetower.instalytics.di.AndroidApplicationComponent
import dev.forcetower.instalytics.di.create

internal class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        val applicationComponent = AndroidApplicationComponent.create()
        applicationComponent.thing.run()
        setContent {
            Instalytics()
        }
    }
}
