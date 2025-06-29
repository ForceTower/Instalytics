package dev.forcetower.instalytics.domain.di

object DomainExternalModule {
    val modules = buildList {
        addAll(DomainAggregatorModule.modules)
        addAll(DomainInternalModule.modules)
    }
}
