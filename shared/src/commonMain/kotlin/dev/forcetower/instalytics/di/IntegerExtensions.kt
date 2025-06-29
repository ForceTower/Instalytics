package dev.forcetower.instalytics.di

fun Int.toInstagramString(): String {
    val n = this
    return when {
        n < 1_000 -> n.toString()
        n < 10_000 -> {
            val value = (n / 100) / 10.0
            val str = value.toString()
            val dotIndex = str.indexOf('.')
            if (dotIndex == -1) {
                "${str}k"
            } else {
                str.substringBefore('.') + "." + str.substringAfter('.').take(1) + "k"
            }
        }
        n < 1_000_000 -> "${n / 1_000}k"
        n < 10_000_000 -> {
            val value = (n / 100_000) / 10.0
            val str = value.toString()
            val dotIndex = str.indexOf('.')
            if (dotIndex == -1) {
                "${str}M"
            } else {
                str.substringBefore('.') + "." + str.substringAfter('.').take(1) + "M"
            }
        }
        else -> "${n / 1_000_000}M"
    }
}
