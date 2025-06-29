package dev.forcetower.instalytics.data.instagram.profile.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class AccountResponse(
    @SerialName("data") val data: List<BasicFacebookAccount>
)

@Serializable
internal data class BasicFacebookAccount(
    @SerialName("instagram_business_account") val businessAccount: BasicIdAccount?,
    @SerialName("id") val id: String
)

@Serializable
internal data class BasicIdAccount(
    @SerialName("id") val id: String
)