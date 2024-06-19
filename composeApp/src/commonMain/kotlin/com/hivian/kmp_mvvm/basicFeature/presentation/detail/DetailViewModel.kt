package com.hivian.kmp_mvvm.basicFeature.presentation.detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.hivian.kmp_mvvm.basicFeature.domain.usecases.GetRandomUserByIdUseCase
import com.hivian.kmp_mvvm.basicFeature.domain.usecases.TranslateResourceUseCase
import com.hivian.kmp_mvvm.basicFeature.domain.usecases.navigation.NavigateBackUseCase
import com.hivian.kmp_mvvm.core.base.ViewModelBase
import com.hivian.kmp_mvvm.core.datasources.ServiceResult
import com.hivian.kmp_mvvm.basicFeature.domain.usecases.ShowAppMessageUseCase
import com.hivian.kmp_mvvm.core.extensions.toErrorMessage
import kotlinx.coroutines.launch


class DetailViewModel(
    private val userId: Int,
    private val translateResourceUseCase: TranslateResourceUseCase,
    private val getRandomUserByIdUseCase: GetRandomUserByIdUseCase,
    private val showAppMessageUseCase: ShowAppMessageUseCase,
    private val navigateBackUseCase: NavigateBackUseCase
): ViewModelBase() {

    val picture = mutableStateOf("")

    val name = mutableStateOf("")

    val email = mutableStateOf("")

    val cell = mutableStateOf("")

    val phone = mutableStateOf("")

    val city = mutableStateOf("")

    val country = mutableStateOf("")

    val latitude = mutableStateOf(0.0)

    val longitude = mutableStateOf(0.0)

    override fun initialize() {
        if (isInitialized.value) return

        viewModelScope.launch {
            when (val result = getRandomUserByIdUseCase(userId)) {
                is ServiceResult.Error -> {
                    showAppMessageUseCase(
                        translateResourceUseCase(result.errorType.toErrorMessage())
                    )
                }
                is ServiceResult.Success -> {
                    picture.value = result.data.picture
                    name.value = result.data.fullName
                    email.value = result.data.email
                    cell.value = result.data.cell
                    phone.value = result.data.phone
                    city.value =result.data.address.city
                    country.value = result.data.address.country
                    latitude.value = result.data.address.latitude
                    longitude.value = result.data.address.longitude
                }
            }
        }

        isInitialized.value = true
    }

    fun navigateBack() {
        navigateBackUseCase()
    }

}
