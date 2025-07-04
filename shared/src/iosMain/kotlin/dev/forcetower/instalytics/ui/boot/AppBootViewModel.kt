package dev.forcetower.instalytics.ui.boot

import androidx.lifecycle.ViewModel
import dev.forcetower.instalytics.domain.model.AppInitialRoute
import dev.forcetower.instalytics.domain.usecase.GetInitialRouteUseCase
import kotlin.experimental.ExperimentalObjCName
import kotlin.experimental.ExperimentalObjCRefinement

@OptIn(ExperimentalObjCRefinement::class, ExperimentalObjCName::class)
open class AppBootViewModel(
    private val getInitialRouteUseCase: GetInitialRouteUseCase
) : ViewModel() {

    suspend fun initial(): AppInitialRoute {
        return getInitialRouteUseCase()
    }
}