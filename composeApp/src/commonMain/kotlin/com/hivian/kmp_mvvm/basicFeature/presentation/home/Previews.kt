package com.hivian.kmp_mvvm.basicFeature.presentation.home

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.hivian.kmp_mvvm.basicFeature.domain.models.Address
import com.hivian.kmp_mvvm.basicFeature.domain.models.RandomUser
import com.hivian.kmp_mvvm.core.base.ViewModelVisualState
import com.hivian.kmp_mvvm.core.datasources.remote.ErrorType
import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider

class HomeViewModelArgProvider: PreviewParameterProvider<HomeViewModelArg> {
    override val values: Sequence<HomeViewModelArg> = sequenceOf(
        HomeViewModelArg(
            randomUsers = mutableStateListOf(),
            isListLoading = mutableStateOf(true),
            title = mutableStateOf("Random Users"),
            errorMessage = mutableStateOf("Network Error"),
            retryMessage = mutableStateOf("Retry"),
            visualState = mutableStateOf(ViewModelVisualState.Loading)
        ),
        HomeViewModelArg(
            randomUsers = mutableStateListOf(),
            isListLoading = mutableStateOf(false),
            title = mutableStateOf("Random Users"),
            errorMessage = mutableStateOf("Network Error"),
            retryMessage = mutableStateOf("Retry"),
            visualState = mutableStateOf(ViewModelVisualState.Error(ErrorType.UNKNOWN))
        ),
        HomeViewModelArg(
            randomUsers = populateList(),
            isListLoading = mutableStateOf(false),
            title = mutableStateOf("Random Users"),
            errorMessage = mutableStateOf("Network Error"),
            retryMessage = mutableStateOf("Retry"),
            visualState = mutableStateOf(ViewModelVisualState.Success)
        )
    )
}

private fun populateList(): SnapshotStateList<RandomUser> {
    val user = RandomUser(
        id = 1,
        gender = "male",
        firstName = "John",
        lastName = "Doe",
        email = "john.doe@mail.com",
        phone = "0606060606",
        cell = "0606060606",
        picture = "",
        address = Address(
            city = "Paris",
            state = "Paris",
            country = "France",
            latitude = 0.0,
            longitude = 0.0,
        )
    )

    val userList = mutableStateListOf<RandomUser>()
    repeat(10) {
        userList.add(user)
    }
    return userList
}

class InitErrorViewArgProvider: PreviewParameterProvider<InitErrorViewArg> {
    override val values: Sequence<InitErrorViewArg> = sequenceOf(
        InitErrorViewArg(errorMessage = "Unknown error", retryMessage = "Retry")
    )
}