package dev.forcetower.instalytics.domain

import me.tatarka.inject.annotations.Inject

@Inject
class ThingDomain internal constructor(
    private val test: OtherTest
) {
    fun run(): String {
        return test.text
    }
}