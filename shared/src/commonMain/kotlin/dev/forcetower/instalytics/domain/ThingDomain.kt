package dev.forcetower.instalytics.domain

import me.tatarka.inject.annotations.Inject

class ThingDomain @Inject internal constructor(
    private val test: OtherTest
) {
    fun run(): String {
        return test.text
    }
}