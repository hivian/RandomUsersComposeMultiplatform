package com.hivian.randomusers.homeFeature.presentation.home

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.hivian.randomusers.core.datasources.ServiceResult
import com.hivian.randomusers.core.datasources.remote.ErrorType
import com.hivian.randomusers.core.domain.base.PaginationViewModel
import com.hivian.randomusers.core.domain.base.ViewModelVisualState
import com.hivian.randomusers.core.domain.extensions.toErrorMessage
import com.hivian.randomusers.core.presentation.navigation.NavigationAction
import com.hivian.randomusers.homeFeature.domain.models.RandomUser
import com.hivian.randomusers.homeFeature.domain.usecases.GetRandomUsersUseCase
import com.hivian.randomusers.homeFeature.domain.usecases.LocalizationUseCase
import com.hivian.randomusers.homeFeature.domain.usecases.ShowAppMessageUseCase
import randomusers.composeapp.generated.resources.Res
import randomusers.composeapp.generated.resources.home_title
import randomusers.composeapp.generated.resources.retry_message
import kotlinx.coroutines.launch

class HomeViewModel(
    private val localizationUseCase: LocalizationUseCase,
    private val getRandomUsersUseCase: GetRandomUsersUseCase,
    private val showAppMessageUseCase: ShowAppMessageUseCase
): PaginationViewModel<Int, RandomUser>(initialKey = PAGINATION_INITIAL_KEY, pageSize = RESULT_COUNT) {

    companion object {
        const val PAGINATION_INITIAL_KEY = 1
        const val RESULT_COUNT = 20
    }

    var showLoadMoreLoader = mutableStateOf(false)

    var title = mutableStateOf("")

    var items = mutableStateListOf<RandomUser>()

    val errorMessage = mutableStateOf("")

    val retryMessage = mutableStateOf("")

    override fun initialize() {
        if (isInitialized.value) return

        viewModelScope.launch {
            title.value = localizationUseCase(Res.string.home_title)
            retryMessage.value = localizationUseCase(Res.string.retry_message)
        }

        loadNext()

        isInitialized.value = true
    }

    override fun getNextKey(currentKey: Int): Int {
        return currentKey + 1
    }

    override suspend fun onRequest(nextKey: Int, pageSize: Int): ServiceResult<List<RandomUser>> {
        return getRandomUsersUseCase(nextKey, pageSize)
    }

    override fun onLoading(initialLoad: Boolean) {
        if (initialLoad) {
            viewModelVisualState.value = ViewModelVisualState.Loading
            return
        }

        showLoadMoreLoader.value = true
    }

    override fun onSuccess(users: List<RandomUser>, initialLoad: Boolean) {
        updateData(users)
        if (users.isEmpty())
            showLoadMoreLoader.value = false

        if (initialLoad) {
            viewModelVisualState.value = ViewModelVisualState.Success
        }
    }

    override fun onError(errorType: ErrorType, users: List<RandomUser>, initialLoad: Boolean) {
        if (initialLoad) {
            if (users.isNotEmpty()) {
                updateData(users)
                viewModelVisualState.value = ViewModelVisualState.Success
            } else {
                viewModelScope.launch {
                    errorMessage.value = localizationUseCase(errorType.toErrorMessage())
                    viewModelVisualState.value = ViewModelVisualState.Error(errorType)
                }
            }
            return
        }

        if (users.isNotEmpty()) {
            updateData(users)
        } else {
            showLoadMoreLoader.value = false
            viewModelScope.launch {
                showAppMessageUseCase(
                    localizationUseCase(errorType.toErrorMessage())
                )
            }
        }
    }

    fun openRandomUserDetail(userId: Int) {
        viewModelScope.launch {
            _navigationEvent.emit(NavigationAction.ToDetailScreen(userId))
        }
    }

    fun refresh() {
        reset()
        loadNext()
    }

    fun loadNext() {
        viewModelScope.launch {
            loadNextItems()
        }
    }

    private fun updateData(users: List<RandomUser>) {
        items.addAll(users)
    }

}
