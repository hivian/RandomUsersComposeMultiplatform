package com.hivian.kmp_mvvm.homeFeature.domain.usecases

import com.hivian.kmp_mvvm.homeFeature.data.mappers.ImageSize
import com.hivian.kmp_mvvm.homeFeature.domain.models.RandomUser
import com.hivian.kmp_mvvm.homeFeature.domain.repository.IRandomUsersRepository
import com.hivian.kmp_mvvm.core.datasources.ServiceResult

class GetRandomUserByIdUseCase(
    private val randomUsersService: IRandomUsersRepository
) {

    suspend operator fun invoke(userId: Int): ServiceResult<RandomUser> {
        return randomUsersService.getUserById(userId, ImageSize.LARGE)
    }

}