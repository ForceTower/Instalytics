package dev.forcetower.instalytics.data.instagram.profile.dto

import kotlinx.serialization.SerialName

@ConsistentCopyVisibility
data class AccountResponse internal constructor(
    @SerialName("data")
    internal val data: List<BasicFacebookAccount>
)

internal data class BasicFacebookAccount(
    @SerialName("instagram_business_account")
    val businessAccount: BasicIdAccount?,
    @SerialName("id")
    val id: String
)

internal data class BasicIdAccount(
    @SerialName("id")
    val id: String
)