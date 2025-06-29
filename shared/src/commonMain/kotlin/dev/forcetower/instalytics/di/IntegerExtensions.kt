package dev.forcetower.instalytics.di

fun Int.toInstagramString(): String {
    val n = this
    return when {
        n < 1_000 -> n.toString()
        n < 10_000 -> String.format("%.1fk", n / 1_000.0)
        n < 1_000_000 -> "${n / 1_000}k"
        n < 10_000_000 -> String.format("%.1fM", n / 1_000_000.0)
        else -> "${n / 1_000_000}M"
    }
}