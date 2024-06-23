package com.hivian.kmp_mvvm.core.domain.base

import com.hivian.kmp_mvvm.core.datasources.remote.ErrorType


sealed class ViewModelVisualState {

    object Loading: ViewModelVisualState()

    data class Error(val errorType: ErrorType): ViewModelVisualState()

    object Success: ViewModelVisualState()

    object Unknown: ViewModelVisualState()

}
