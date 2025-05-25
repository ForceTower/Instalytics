package dev.forcetower.instalytics.android.ui.navigation.login.facebook

import android.app.Activity
import androidx.activity.result.ActivityResultRegistryOwner
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import dev.forcetower.instalytics.toolkit.lifecycle.viewmodel.EventViewModel
import dev.forcetower.kmm.toolkit.logdog.logdog

internal sealed interface FacebookLoginEvent {
    data object LoginSuccess : FacebookLoginEvent
    data object LoginFailed : FacebookLoginEvent
    data object LoginCanceled : FacebookLoginEvent
}

internal class FacebookLoginViewModel : EventViewModel<FacebookLoginEvent>() {
    private val callbackManager = CallbackManager.Factory.create()
    init {
        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult) {
                    onFacebookLoginSuccess(result.accessToken)
                }
                override fun onCancel() {
                    onFacebookLoginCancel()
                }
                override fun onError(error: FacebookException) {
                    onFacebookLoginError(error)
                }
            }
        )
    }

    fun login(activity: Activity) {
        LoginManager.getInstance().logOut()
        LoginManager
            .getInstance()
            .logIn(
                activity as ActivityResultRegistryOwner,
                callbackManager,
                listOf(
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
            )
    }

    private fun onFacebookLoginSuccess(accessToken: AccessToken) {
        logdog { "Access token ${accessToken.token}" }
        sendEvent { FacebookLoginEvent.LoginSuccess }
    }

    private fun onFacebookLoginCancel() {
        sendEvent { FacebookLoginEvent.LoginCanceled }
    }

    private fun onFacebookLoginError(error: FacebookException) {
        sendEvent { FacebookLoginEvent.LoginFailed }
    }
}