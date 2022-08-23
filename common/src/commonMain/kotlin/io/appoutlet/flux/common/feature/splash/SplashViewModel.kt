package io.appoutlet.flux.common.feature.splash

import io.appoutlet.flux.common.feature.BaseViewModel
import io.appoutlet.flux.common.util.RetryTrigger
import io.appoutlet.flux.common.util.retryableFlow
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

@FlowPreview
class SplashViewModel(
    private val splashOrchestrator: SplashOrchestrator,
    private val splashUiStateMapper: SplashUiStateMapper,
) : BaseViewModel() {
    private val splashRetryTrigger = RetryTrigger()

    val uiState = retryableFlow(splashRetryTrigger) {
        splashOrchestrator.verifyLoggedUser()
            .map { splashUiStateMapper.map(it) }
            .onStart { emit(SplashUiState.Loading) }
            .catch {
                val errorState = splashUiStateMapper.map(it)
                emit(errorState)
            }
    }
}

sealed class SplashUiState {
    object Loading : SplashUiState()
    object Logged : SplashUiState()
    object EmailNotVerified : SplashUiState()
    object NotLogged : SplashUiState()
    object Error : SplashUiState()
}
