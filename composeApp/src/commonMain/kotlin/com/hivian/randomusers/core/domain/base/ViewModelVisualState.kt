package com.hivian.randomusers.core.domain.base

import com.hivian.randomusers.core.datasources.remote.ErrorType


sealed class ViewModelVisualState {

    object Loading: ViewModelVisualState()

    data class Error(val errorType: ErrorType): ViewModelVisualState()

    object Success: ViewModelVisualState()

    object Unknown: ViewModelVisualState()

}
