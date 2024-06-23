package com.hivian.kmp_mvvm.homeFeature.presentation.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.hivian.kmp_mvvm.homeFeature.domain.models.RandomUser
import com.hivian.kmp_mvvm.core.domain.base.ViewModelVisualState

data class InitErrorViewArg(
    val errorMessage: String,
    val retryMessage: String,
)

data class HomeViewModelArg(
    val randomUsers: SnapshotStateList<RandomUser>,
    val isListLoading: MutableState<Boolean>,
    val title: MutableState<String>,
    val errorMessage: MutableState<String>,
    val retryMessage: MutableState<String>,
    val visualState: MutableState<ViewModelVisualState> = mutableStateOf(ViewModelVisualState.Loading),
    val refresh: () -> Unit = {},
    val loadNext: () -> Unit = {},
    val openDetail: (Int) -> Unit = {},
)