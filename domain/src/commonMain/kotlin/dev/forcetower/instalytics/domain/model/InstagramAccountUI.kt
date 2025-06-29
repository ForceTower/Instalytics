package dev.forcetower.instalytics.domain.model

data class InstagramAccountUI(
    val id: String = "",
    val name: String = "",
    val username: String = "",
    val profilePictureUrl: String? = null,
    val biography: String? = null,
    val followers: Int = 0,
    val follows: Int = 0,
    val mediaCount: Int = 0
)
