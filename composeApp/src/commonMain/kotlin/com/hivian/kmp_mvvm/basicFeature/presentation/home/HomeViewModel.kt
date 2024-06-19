package com.hivian.kmp_mvvm.basicFeature.presentation.home

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.hivian.kmp_mvvm.basicFeature.domain.models.RandomUser
import com.hivian.kmp_mvvm.basicFeature.domain.usecases.GetRandomUsersUseCase
import com.hivian.kmp_mvvm.basicFeature.domain.usecases.LocalizationUseCase
import com.hivian.kmp_mvvm.basicFeature.domain.usecases.navigation.NavigateToRandomUserDetailUseCase
import com.hivian.kmp_mvvm.core.base.PaginationViewModel
import com.hivian.kmp_mvvm.core.base.ViewModelVisualState
import com.hivian.kmp_mvvm.core.services.ILocalizationService
import com.hivian.kmp_mvvm.basicFeature.domain.usecases.ShowAppMessageUseCase
import com.hivian.kmp_mvvm.core.datasources.ServiceResult
import com.hivian.kmp_mvvm.core.datasources.remote.ErrorType
import com.hivian.kmp_mvvm.core.extensions.toErrorMessage
import kmp_mvvm.composeapp.generated.resources.Res
import kmp_mvvm.composeapp.generated.resources.home_title
import kotlinx.coroutines.launch

class HomeViewModel(
    private val localizationUseCase: LocalizationUseCase,
    private val navigateToRandomUserDetailUseCase: NavigateToRandomUserDetailUseCase,
    private val getRandomUsersUseCase: GetRandomUsersUseCase,
    private val showAppMessageUseCase: ShowAppMessageUseCase
): PaginationViewModel<Int, RandomUser>(initialKey = PAGINATION_INITIAL_KEY, pageSize = RESULT_COUNT) {

    companion object {
        const val PAGINATION_INITIAL_KEY = 1
        const val RESULT_COUNT = 20
    }

    var showLoadMoreLoader = mutableStateOf(false)

    var title : String = localizationUseCase(Res.string.home_title)

    var items = mutableStateListOf<RandomUser>()

    val errorMessage : String
        get() = when (val state = viewModelVisualState.value) {
            is ViewModelVisualState.Error -> {
                localizationUseCase(state.errorType.toErrorMessage())
            }
            else -> ""
        }

    val retryMessage: String = localizationUseCase(R.string.retry_message)

    override fun initialize() {
        if (isInitialized.value) return

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
                viewModelVisualState.value = ViewModelVisualState.Error(errorType)
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
        navigateToRandomUserDetailUseCase(userId)
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
