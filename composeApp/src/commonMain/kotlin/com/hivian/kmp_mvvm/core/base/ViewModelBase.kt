package com.hivian.kmp_mvvm.core.base

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.hivian.kmp_mvvm.core.services.navigation.NavigationAction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class ViewModelBase: ViewModel() {

    protected val _navigationEvent = MutableStateFlow<NavigationAction?>(null)
    val navigationEvent: StateFlow<NavigationAction?> = _navigationEvent.asStateFlow()

    var isInitialized = MutableStateFlow(false)
        protected set

    val viewModelVisualState = mutableStateOf<ViewModelVisualState>(ViewModelVisualState.Unknown)

    open fun initialize() {}

}
